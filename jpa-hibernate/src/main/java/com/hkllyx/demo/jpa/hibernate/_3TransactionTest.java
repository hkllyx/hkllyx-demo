package com.hkllyx.demo.jpa.hibernate;

import com.hkllyx.demo.jpa.hibernate.entity.Employee;
import com.hkllyx.demo.jpa.hibernate.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.transaction.Status;
import javax.transaction.Synchronization;

/**
 * 事务演示
 *
 * @author xiaoyong3
 * @date 2023/04/26
 */
@Slf4j
public class _3TransactionTest {
    private static SessionFactory sessionFactory;
    private static Synchronization synchronization;

    @BeforeAll
    static void beforeAll() {
        sessionFactory = HibernateUtil.defaultSessionFactory();
        synchronization = new Synchronization() {
            @Override
            public void beforeCompletion() {
                log.warn("事务完成前处理");
            }

            @Override
            public void afterCompletion(int status) {
                if (Status.STATUS_COMMITTED == status) {
                    log.warn("事务已提交");
                } else if (Status.STATUS_ROLLEDBACK == status) {
                    log.warn("事务已回滚");
                }
            }
        };
    }

    @AfterAll
    static void afterAll() {
        HibernateUtil.close();
    }

    @Test
    void testCommit() {
        try (Session session = sessionFactory.openSession()) {
            // 开启事务
            Transaction transaction = session.beginTransaction();
            // 注册事务同步器，事务结束前后回调接口
            transaction.registerSynchronization(synchronization);
            // 设置事务超时时间，单位：秒
            transaction.setTimeout(10);
            try {
                Employee employee = new Employee();
                // 提交事务
                transaction.commit();
            } catch (Exception e) {
                // 出现异常后如果事务还存活，回滚事务
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            }
        } finally {
            HibernateUtil.close();
        }
    }
}
