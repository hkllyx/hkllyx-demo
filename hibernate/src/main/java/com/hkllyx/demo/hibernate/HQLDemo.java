package com.hkllyx.demo.hibernate;

import com.hkllyx.demo.hibernate.entity.Employee;
import com.hkllyx.demo.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * Hibernate Query Language演示
 *
 * @author xiaoyong3
 * @date 2023/04/24
 */
public class HQLDemo {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.newSession()) {
            String hql = "FROM Employee e WHERE e.no = :no AND e.name = ?1";
            Query<Employee> query = session.createQuery(hql, Employee.class);
            // `:xxx`的形式设置参数
            query.setParameter("no", "N001");
            // `?1`的形式设置参数
            query.setParameter(1, "zhangsan");
            // 唯一键查询
            Employee result = query.uniqueResult();
            System.out.println(result);
            // 自动联表查询
            if (result != null) {
                System.out.println(result.getDepartment());
            }

            hql = "FROM Employee e WHERE e.no = :no AND e.name = :name";
            query = session.createQuery(hql, Employee.class);
            // `:xxx`可以通过对象赋值
            Employee param = new Employee();
            param.setNo("N001");
            param.setName("zhangsan");
            query.setProperties(param);
            result = query.uniqueResult();
            System.out.println(result);
        } finally {
            HibernateUtil.closeSessionFactory();
        }
    }
}
