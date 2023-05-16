package com.hkllyx.demo.jpa.hibernate;

import com.hkllyx.demo.jpa.hibernate.entity.Employee;
import com.hkllyx.demo.jpa.hibernate.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Hibernate原生启动演示
 *
 * @see <a href="https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html">Hibernate User Guide</a>
 * @author xiaoyong3
 * @date 2023/04/10
 */
@Slf4j
public class _1NativeBootstrappingDemo {

    public static void main(String[] args) {
        /*
        更多见HibernateUtil的JavaDoc，一个简要完整的演示：
        try (StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build()) {
            MetadataSources metadataSources = new MetadataSources(serviceRegistry);
            Metadata metadata = metadataSources.buildMetadata();
            try (SessionFactory sessionFactory = metadata.buildSessionFactory()){
                try (Session session = sessionFactory.openSession()) {
                   // do something with session
                }
            }
        }
        */
        try (Session session = HibernateUtil.newSession()) {
            // Transaction用于划分事务边界，非线程安全。Transaction继承了JPA标准的EntityTransaction。
            // 开启事务
            Transaction transaction = session.beginTransaction();
            try {
                // 一些业务操作
                Employee employee = session.get(Employee.class, 1L);
                log.warn("get entity: {}", employee);

                // 提交事务
                transaction.commit();
            } catch (Exception e) {
                // 异常回滚事务
                if (transaction.isActive() || transaction.getRollbackOnly()) {
                    transaction.rollback();
                }
                throw e;
            }
        } finally {
            HibernateUtil.close();
        }
    }
}
