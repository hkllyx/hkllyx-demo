package com.hkllyx.demo.jpa.hibernate;

import com.hkllyx.demo.jpa.hibernate.util.JPAUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * 从JPA中访问Hibernate原生API
 *
 * @author xiaoyong3
 * @date 2023/05/15
 */
public class _3AccessNativeAPIFromJPADemo {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = JPAUtil.defualtEntityManagerFactory();
        EntityManager entityManager = JPAUtil.newEntityManager();
        try {
            // 获取SessionFactory
            SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
            // 获取Session
            Session session = entityManager.unwrap(Session.class);
        } finally {
            entityManager.close();
            JPAUtil.close();
        }
    }
}
