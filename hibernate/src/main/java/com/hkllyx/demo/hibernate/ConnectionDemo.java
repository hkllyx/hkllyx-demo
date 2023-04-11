package com.hkllyx.demo.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author xiaoyong3
 * @date 2023/04/10
 */
public class ConnectionDemo {

    public static void main(String[] args) {
        // Configuration主要用于管理Hibernate配置信息，以及创建SessionFactory实例
        // 获取配置，默认会读取classpath:hibernate.properties和classpath:hibernate.cfg.xml中的配置（重复配置后者优先级更高）
        Configuration configuration = new Configuration().configure();

        // SessionFactory用于读取和解析映射，并负责创建和管理Session对象
        // SessionFactory保存了当前的数据库配置信息、所有映射关系以及Hibernate自动生成的预定义SQL语句，同时它还维护了Hibernate的二级缓存（重量级原因）
        // 特点：
        //   - 线程安全，它的同一个实例可以被应用多个不同的线程共享
        //   - 重量级，不能随意创建和销毁它的实例。应用需要创建的SessionFactory实例的数量应该是等同于它需要访问的数据库数量
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {

            // Session是Hibernate应用程序与数据库进行交互时，使用最广泛的接口，它也被称为Hibernate的持久化管理器，所有持久化对象必须在Session的管理下才可以进行持久化操作。持久化类只有与Session关联起来后，才具有了持久化的能力
            // Session对象维护了Hibernate的一级缓存，在显式执行flush之前，所有的持久化操作的数据都缓存在Session对象中
            // 特点：
            //   - 非线程安全，因此应该避免多个线程共享同一个Session实例
            //   - 轻量级，它的创建和销毁不需要消耗太多的资源。通常我们会将每一个Session实例和一个数据库事务绑定，每执行一个数据库事务，不论执行成功与否，最后都因该调用Session的Close()方法，关闭Session释放占用的资源
            // 获取方式：
            //   - sessionFactory.openSession()，SessionFactory直接创建一个新的Session实例，且在使用完成后需要调用close()方法进行手动关闭
            //   - sessionFactory.getCurrentSession()，创建的Session实例会被绑定到当前线程中，它在事务提交或回滚后会自动关闭
            try(Session session = sessionFactory.openSession()) {
            }
        }
    }
}
