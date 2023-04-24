package com.hkllyx.demo.hibernate;

import com.hkllyx.demo.hibernate.entity.Employee;
import com.hkllyx.demo.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * JPA API演示
 *
 * @author xiaoyong3
 * @date 2023/04/24
 */
public class CriteriaDemo {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.newSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery.select(root);
            System.out.println("案例1：" + session.createQuery(criteriaQuery).list());

            // where条件
            Predicate eqNoPredicate = criteriaBuilder.equal(root.get("no"), "N001");
            Predicate eqNamePredicate = criteriaBuilder.equal(root.get("name"), "zhangsan");
            // and
            criteriaQuery.where(eqNoPredicate, eqNamePredicate);
            // 等效 criteriaQuery.where(criteriaBuilder.and(eqNoPredicate, eqNamePredicate));
            // 等效 criteriaQuery.where(eqNoPredicate).where(eqNamePredicate);
            System.out.println("案例2：" + session.createQuery(criteriaQuery).list());

            // or
            criteriaQuery.where(criteriaBuilder.or(eqNoPredicate, eqNamePredicate));
            System.out.println("案例3：" + session.createQuery(criteriaQuery).list());

            // count
            CriteriaQuery<Long> countCriteriaQuery = criteriaBuilder.createQuery(Long.class);
            // 需要显示地指定查询表
            countCriteriaQuery.from(Employee.class);
            countCriteriaQuery.select(criteriaBuilder.count(root));
            System.out.println("案例4：" + session.createQuery(countCriteriaQuery).getSingleResult());
        } finally {
            HibernateUtil.closeSessionFactory();
        }
    }
}
