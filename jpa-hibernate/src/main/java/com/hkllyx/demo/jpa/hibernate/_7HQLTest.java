package com.hkllyx.demo.jpa.hibernate;

import com.hkllyx.demo.jpa.hibernate.entity.Employee;
import com.hkllyx.demo.jpa.hibernate.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Arrays;

/**
 * Hibernate Query Language演示
 *
 * @author xiaoyong3
 * @date 2023/04/24
 */
@Slf4j
public class _7HQLTest {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.newSession()) {
            // --------------------------------- query ---------------------------------
            String hql = "FROM Employee e WHERE e.id = :id AND e.no = :no AND e.version >= ?1";
            Query<Employee> query = session.createQuery(hql, Employee.class);

            // `:xxx`形式绑定参数
            query.setParameter("id", 2L);

            // `:xxx`形式也可以使用对象绑定参数。注意，对象的null值也会被绑定
            Employee propObj = new Employee();
            propObj.setId(1L); // 覆盖之前绑定的参数
            propObj.setNo("N001");
            query.setProperties(propObj);

            // `?1`形式绑定参数，`?`后的数字必须从1开始
            query.setParameter(1, 0);

            // 唯一键查询
            Employee result = query.uniqueResult();
            log.warn("查询：{}", result);

            if (result != null) {
                // 查询关联表
                log.warn("查询关联表：{}", result.getDepartment());
            }

            // 查询特定属性，返回对象是每条记录一个Object数组，如果createQuery时指定为Employee会抛错
            hql = "SELECT e.id, e.no, e.name FROM Employee e WHERE e.id = :id";
            Query<Object[]> specColQuery = session.createQuery(hql, Object[].class);
            specColQuery.setParameter("id", 1L);
            Object[] cols = specColQuery.uniqueResult();
            log.warn("只查询特定属性：data={}", Arrays.toString(cols));

            // --------------------------------- aggregate ---------------------------------

            // count
            Query<Long> countQuery = session.createQuery("SELECT count(*) FROM Employee e", Long.class);
            Long count = countQuery.getSingleResult();
            log.warn("聚合查询：{}", count);

            // --------------------------------- update ---------------------------------
            // 更新/删除要求事务内执行
            Transaction transaction = session.beginTransaction();

            // 更新/删除sql不允许带有类型，比如session.createQuery(hql, Integer.class)
            Query<?> updateQuery = session.createQuery("UPDATE Employee e SET e.name = :name WHERE id = :id");
            updateQuery.setParameter("name", "zhangsan_new");
            updateQuery.setParameter("id", 1L);
            int updateRows = updateQuery.executeUpdate();
            log.warn("更新：{}", updateRows);

            // --------------------------------- delete ---------------------------------
            Query<?> deleteQuery = session.createQuery("DELETE FROM Employee WHERE id = :id");
            deleteQuery.setParameter("id", 1L);
            int deleteRows = deleteQuery.executeUpdate();
            log.warn("删除：{}", deleteRows);

            transaction.commit();
        } finally {
            HibernateUtil.close();
        }
    }
}
