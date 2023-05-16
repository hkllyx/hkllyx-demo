package com.hkllyx.demo.jpa.hibernate;

import com.hkllyx.demo.jpa.hibernate.entity.Department;
import com.hkllyx.demo.jpa.hibernate.util.JPAUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;

/**
 * 批处理演示
 *
 * @author xiaoyong3
 * @date 2023/04/25
 */
@Slf4j
public class _9BatchingTest {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static SessionFactory sessionFactory;
    private static Session session;

    @BeforeAll
    static void beforeAll() {
        entityManagerFactory = JPAUtil.defualtEntityManagerFactory();
        entityManager = entityManagerFactory.createEntityManager();
        sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        session = entityManager.unwrap(Session.class);
    }

    @AfterAll
    static void afterAll() {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void nativeWay() {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            for (int i = 0; i < 100_000; i++) {
                Department department = new Department();
                department.setCode(String.format("D%06d", i + 3));
                department.setName(department.getCode());
                department.setRegisterDate(LocalDate.now());
                // 持久化上下文中会持有大量的实例，容易导致OOM
                // 注意：使用了主键生成器，hibernate.jdbc.batch_size不会生效
                entityManager.persist(department);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive() || transaction.getRollbackOnly()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Test
    void manualFlushAndClear() {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            int batchSize = 50;
            for (int i = 0; i < 100_000; i++) {
                // 手动flush并且清理上下文
                if (i % batchSize == 0) {
                    entityManager.flush();
                    entityManager.clear();
                }

                Department department = new Department();
                department.setCode(String.format("D%06d", i + 3));
                department.setName(department.getCode());
                department.setRegisterDate(LocalDate.now());
                entityManager.persist(department);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive() || transaction.getRollbackOnly()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Test
    void sessionScroll() {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            try (ScrollableResults scrollableResults = session
                    .createQuery("select d from Department d")
                    .setCacheMode(CacheMode.IGNORE)
                    .scroll(ScrollMode.FORWARD_ONLY)) {

                int batchSize = 50, count = 0;
                while (scrollableResults.next()) {
                    Department department = (Department) scrollableResults.get(0);
                    log.warn("data={}", department);

                    // 手动flush并且清理上下文
                    if (count++ % batchSize == 0) {
                        entityManager.flush();
                        entityManager.clear();
                    }
                }
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive() || transaction.getRollbackOnly()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Test
    void statelessSession() {
        /*
         * Stateless Session是一种面向命令的、不关联持久化上下文的Session。它更接近于JDBC，有以下特点：
         *   - 没有一级缓存
         *   - 不和二级缓存打交道
         *   - 没有事务性回写和自动脏检查
         * 因此，持久化操作（SQL执行）是同步发生的，且通过Stateless Session获取的实例总是detached的。
         * 使用Stateless Session有以下限制：
         *   - 它的操作永远不会级联相关实例
         *   - 集合会被忽略
         *   - 关联的懒加载是不透明的，只能通过显示fetch()获取
         *   - 它的操作会绕过Hibernate事件模型和拦截器
         */
        try (StatelessSession statelessSession = sessionFactory.openStatelessSession()) {
            Transaction transaction = statelessSession.getTransaction();
            try {
                transaction.begin();

                for (int i = 0; i < 100_000; i++) {
                    Department department = new Department();
                    department.setCode(String.format("D%06d", i + 3));
                    department.setName(department.getCode());
                    department.setRegisterDate(LocalDate.now());
                    statelessSession.insert(department);
                }

                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive() || transaction.getRollbackOnly()) {
                    transaction.rollback();
                }
            }
        }
    }
}
