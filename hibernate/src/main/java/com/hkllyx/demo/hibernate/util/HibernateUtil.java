package com.hkllyx.demo.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

/**
 * @author xiaoyong3
 * @date 2023/04/24
 */
public class HibernateUtil {
    /** 默认配置 */
    private static Configuration configuration;
    /** 默认Session工厂 */
    private static SessionFactory sessionFactory;

    private HibernateUtil() {
    }

    /** 获取默认配置 */
    public static Configuration defaultConfiguration() {
        if (configuration != null) {
            return configuration;
        }
        configuration = new Configuration().configure();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        return configuration;
    }

    /** 获取默认Session工厂 */
    public static SessionFactory defaultSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            return sessionFactory;
        }
        sessionFactory = defaultConfiguration().buildSessionFactory();
        return sessionFactory;
    }

    /** 关闭默认Session工厂 */
    public static void closeSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }

    public static Session newSession() {
        return defaultSessionFactory().openSession();
    }

    public static Session currentSession() {
        return defaultSessionFactory().getCurrentSession();
    }
}
