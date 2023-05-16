package com.hkllyx.demo.jpa.hibernate;

import com.hkllyx.demo.jpa.hibernate.entity.Employee;
import com.hkllyx.demo.jpa.hibernate.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * 命名查询演示
 *
 * @author xiaoyong3
 * @date 2023/04/25
 */
@Slf4j
public class _8NamedQueryTest {

    @Test
    void findAll() {
        try (Session session = HibernateUtil.newSession()) {
            Query<Employee> namedQuery = session.getNamedQuery("Employee.findAll");
            List<Employee> result = namedQuery.list();
            log.warn("查询结果：{}", result);
        } finally {
            HibernateUtil.close();
        }
    }
}
