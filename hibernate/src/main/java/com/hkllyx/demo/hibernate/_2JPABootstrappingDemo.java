package com.hkllyx.demo.hibernate;

import com.hkllyx.demo.hibernate.entity.Employee;
import com.hkllyx.demo.hibernate.util.JPAUtil;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * JPA启动（Jakarta Persistence Bootstrapping）演示
 *
 * @author xiaoyong3
 * @date 2023/04/10
 */
@Slf4j
public class _2JPABootstrappingDemo {

    public static void main(String[] args) {
        /*
         更多见JPAUtil的JavaDoc，一个简要完整的演示：
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo-persistence-unit");
         EntityManager em = emf.createEntityManager();
         // do something with em...
         entityManager.close();
         emf.createEntityManager();
         */
        EntityManager entityManager = JPAUtil.newEntityManager();
        try {
            // 获取事务
            EntityTransaction transaction = entityManager.getTransaction();
            // 开启事务
            transaction.begin();
            try {
                // 一些业务操作
                Employee employee = entityManager.find(Employee.class, 1L);
                log.warn("find entity: {}", employee);

                // 提交事务
                transaction.commit();
            } catch (Exception e) {
                // 异常回滚事务
                transaction.rollback();
                throw e;
            }
        } finally {
            JPAUtil.close();
        }
    }
}
