package com.hkllyx.demo.jpa.hibernate;

import com.hkllyx.demo.jpa.hibernate.entity.Department;
import com.hkllyx.demo.jpa.hibernate.interceptor.CustomInterceptor;
import com.hkllyx.demo.jpa.hibernate.listener.CustomerLoadEventListener;
import com.hkllyx.demo.jpa.hibernate.util.JPAUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;

/**
 * 拦截器&事件演示
 *
 * @author xiaoyong3
 * @date 2023/04/25
 */
@Slf4j
public class _11InterceptorAndEventDemo {
    private static EntityManagerFactory entityManagerFactory;
    private static SessionFactory sessionFactory;

    @BeforeAll
    static void beforeAll() {
        entityManagerFactory = JPAUtil.defualtEntityManagerFactory();
        sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

    @AfterAll
    static void afterAll() {
        entityManagerFactory.close();
    }

    @Test
    void test() {
        // 注册监听器
        sessionFactory.unwrap(SessionFactoryImplementor.class).getServiceRegistry()
                .getService(EventListenerRegistry.class)
                .appendListeners(EventType.LOAD, CustomerLoadEventListener.class);

        // 注册拦截器
        sessionFactory.withOptions().interceptor(new CustomInterceptor());

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Department department = session.get(Department.class, 1L);
                log.warn("data={}, registerDays={}", department, department.getRegisterDays());
                department.setName(department.getName() + "_NEW");

                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive() || transaction.getRollbackOnly()) {
                    transaction.rollback();
                }
            }
        }
    }
}
