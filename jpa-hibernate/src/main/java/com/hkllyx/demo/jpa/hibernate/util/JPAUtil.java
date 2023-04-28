package com.hkllyx.demo.jpa.hibernate.util;

import javax.persistence.*;
import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceUnitInfo;

/**
 * @author xiaoyong3
 * @date 2023/04/27
 */
public class JPAUtil {
    /** 默认EntityManagerFactory */
    private static EntityManagerFactory entityManagerFactory;

    private JPAUtil() {
    }

    /**
     * 第1步：构建EntityManagerFactory实例。在JavaEE容器中，可以通过{@link PersistenceUnit}注解注入EntityManagerFactory。<br>
     * 构建过程中，会通过PersistenceXmlParser#doResolve读取META-INF/persistence.xml配置文件中的配置，<br>
     * 如果没有META-INF/persistence.xml配置文件，也可以实现{@link PersistenceUnitInfo}来配置，
     * 然后通过{@link PersistenceProvider#createContainerEntityManagerFactory(PersistenceUnitInfo, Map)}方法构建EntityManagerFactory实例。
     */
    public static EntityManagerFactory defualtEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("demo-persistence-unit");
        }
        return entityManagerFactory;
    }

    /**
     * 第2步：构建EntityManager实例。在JavaEE容器中，可以通过{@link PersistenceContext}注解注入EntityManager。
     */
    public static EntityManager newEntityManager() {
        return defualtEntityManagerFactory().createEntityManager();
    }

    /** 释放资源 */
    public static void close() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
