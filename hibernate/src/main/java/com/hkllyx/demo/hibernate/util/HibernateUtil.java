package com.hkllyx.demo.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

/**
 * @author xiaoyong3
 * @date 2023/04/24
 */
public class HibernateUtil {
    /** 默认ServiceRegistry */
    private static StandardServiceRegistry serviceRegistry;
    /** 默认配置Metadata */
    private static Metadata metadata;
    /** 默认SessionFactory */
    private static SessionFactory sessionFactory;

    private HibernateUtil() {
    }

    /**
     * ServiceRegistry持有Hibernate启动和运行中需要的服务。ServiceRegistry分为2种：<br>
     * <ul>
     *     <li>
     *         org.hibernate.boot.registry.BootstrapServiceRegistry：持有3种服务：
     *         <ul>
     *             <li>org.hibernate.boot.registry.classloading.spi.ClassLoaderService：控制Hibernate和ClassLoader的交互。</li>
     *             <li>org.hibernate.integrator.spi.IntegratorService：发现和管理org.hibernate.integrator.spi.Integrator实例。</li>
     *             <li>StrategySelector：控制Hibernate如果繁多策略的实现，是非常强大的服务。</li>
     *         </ul>
     *     </li>
     *     <li>org.hibernate.boot.registry.StandardServiceRegistry。用户值一般需要配置的是StandardServiceRegistry。</li>
     * </ul>
     * <p>
     * 第1步：构建ServiceRegistry实例。<br>
     * 实例化StandardServiceRegistryBuilder时，会通过{@link Environment#getProperties}读取hibernate.properties文件的配置。<br>
     * 此外，可以通过{@link StandardServiceRegistryBuilder#loadProperties}额外读取指定properties文件。<br>
     * {@link StandardServiceRegistryBuilder#configure}默认读取hibernate.cfg.xml配置文件，也可以指定配置文件。
     */
    public static StandardServiceRegistry defaultServiceRegistry() {
        if (serviceRegistry == null) {
            // 构建ServiceRegistry实例
            serviceRegistry = new StandardServiceRegistryBuilder()
                    .loadProperties("hibernate-bak.properties")
                    .configure("hibernate-bak.cfg.xml")
                    .build();
        }
        return serviceRegistry;
    }

    /**
     * MetadataSources用于配置Hibernate实体映射资源，可以通过Entity类类型、类名、包、hbm.xml文件配置实体映射，
     * 也可以不额外配置，通过hibernate.cfg.xml文件来配置实体映射。
     * <p>
     * 第2步：构建MetadataSources实例。
     * <p>
     * <p>
     * Metadata代表着一个ORM模型，持有所有Hibernate实体映射、类型映射、命名查询、数据库信息等。
     * <p>
     * 第3步：构建Metadata实例。<br>
     * 如果需要配置Metadata的其他属性，如命名策略、基础类型映射、属性转换器等，
     * 可以先通过MetadataSources的getMetadataBuilder方法先获取MetadataBuilder进行配置，
     * 否则直接使用MetadataSources的buildMetadata方法构建Metadata实例。
     */
    public static Metadata defaultMetadata() {
        if (metadata == null) {
            // 构建MetadataSources实例
            MetadataSources metadataSources = new MetadataSources(defaultServiceRegistry());
            // 构建Metadata实例
            metadata = metadataSources.buildMetadata();
        }
        return metadata;
    }

    /**
     * SessionFactory用于读取和解析映射，并负责创建和管理Session实例。<br>
     * SessionFactory保存了当前的数据库配置信息、所有映射关系以及Hibernate自动生成的预定义SQL语句，
     * 同时它还维护了Hibernate的二级缓存（重量级原因）。<br>
     * SessionFactory继承了JPA标准的EntityManagerFactory。<br>
     * 特点：
     * <ul>
     *     <li>线程安全：同一个实例可以被应用多个不同的线程共享。</li>
     *     <li>重量级：不能随意创建和销毁它的实例。应用需要创建的SessionFactory实例的数量应该是等同于它需要访问的数据库数量。</li>
     * </ul>
     * <p>
     * 第4步：构建SessionFactory实例。<br>
     * 如果需要额外配置SessionFactory，如设定名称、添加Interceptor等，
     * 可以先通过Metadata的getSessionFactoryBuilder方法先获取SessionFactoryBuilder进行配置，
     * 否则直接使用Metadata的buildSessionFactory方法构建SessionFactory实例。
     */
    public static SessionFactory defaultSessionFactory() {
        if (sessionFactory == null) {
            // 构建SessionFactory实例
            sessionFactory = defaultMetadata().buildSessionFactory();
        }
        return sessionFactory;
    }

    /**
     * Session是Hibernate应用程序与数据库进行交互时，使用最广泛的接口，
     * 它也被称为Hibernate的持久化管理器，所有持久化对象必须在Session的管理下才可以进行持久化操作。
     * 持久化类只有与Session关联起来后，才具有了持久化的能力。<br>
     * Session实例维护了Hibernate的一级缓存，在显式flush之前，所有的持久化操作的数据都缓存在Session实例中。<br>
     * Session继承了JPA标准的EntityManager。<br>
     * 特点：
     * <ul>
     *     <li>非线程安全：因此应该避免多个线程共享同一个Session实例。</li>
     *     <li>
     *         轻量级：它的创建和销毁不需要消耗太多的资源。通常我们会将每一个Session实例和一个数据库事务绑定，
     *         每执行一个数据库事务，不论执行成功与否，最后都因该调用Session的close方法，关闭Session释放占用的资源。
     *     </li>
     * </ul>
     * Session有2种获取方式：
     * <ul>
     *     <li>sessionFactory.openSession()：SessionFactory直接创建一个新的Session实例，且在使用完成后需要调用close方法进行手动关闭。</li>
     *     <li>sessionFactory.getCurrentSession()：创建的Session实例会被绑定到当前线程中，它在事务提交或回滚后会自动关闭。</li>
     * </ul>
     * <p>
     * 第5步：构建Session实例。
     */
    public static Session newSession() {
        // 构建Session实例
        return defaultSessionFactory().openSession();
    }

    /** 获取当前Session */
    public static Session currentSession() {
        // 获取当前Session实例
        return defaultSessionFactory().getCurrentSession();
    }

    /** 释放资源 */
    public static void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
        if (serviceRegistry != null) {
            serviceRegistry.close();
        }
    }
}
