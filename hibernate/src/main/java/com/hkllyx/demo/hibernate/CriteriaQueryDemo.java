package com.hkllyx.demo.hibernate;

import com.hkllyx.demo.hibernate.entity.Employee;
import com.hkllyx.demo.hibernate.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * 标准查询语言演示
 *
 * @author xiaoyong3
 * @date 2023/04/24
 */
@Slf4j
public class CriteriaQueryDemo {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.newSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            // --------------------------------- query ---------------------------------
            CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
            // 指定查询表
            Root<Employee> root = criteriaQuery.from(Employee.class);
            // 指定查询的字段
            // criteriaQuery.select(root);
            // 查询指定字段，entity需要相应字段的构造器
            criteriaQuery.multiselect(root.get("id"), root.get("no"), root.get("name"));
            List<Employee> listResult = session.createQuery(criteriaQuery).list();
            log.warn("无条件查询：{}", listResult);

            // where条件
            Predicate eqNoPredicate = criteriaBuilder.equal(root.get("no"), "N001");
            Predicate eqNamePredicate = criteriaBuilder.equal(root.get("name"), "zhangsan");
            // and
            criteriaQuery.where(eqNoPredicate, eqNamePredicate);
            // 等效 criteriaQuery.where(criteriaBuilder.and(eqNoPredicate, eqNamePredicate));
            // 等效 criteriaQuery.where(eqNoPredicate).where(eqNamePredicate);
            listResult = session.createQuery(criteriaQuery).list();
            log.warn("and查询：{}", listResult);

            // or
            criteriaQuery.where(criteriaBuilder.or(eqNoPredicate, eqNamePredicate));
            listResult = session.createQuery(criteriaQuery).list();
            log.warn("or查询：{}", listResult);

            // --------------------------------- aggregate ---------------------------------

            // count
            CriteriaQuery<Long> countCriteriaQuery = criteriaBuilder.createQuery(Long.class);
            countCriteriaQuery.from(Employee.class);
            countCriteriaQuery.select(criteriaBuilder.count(root));
            Long countResult = session.createQuery(countCriteriaQuery).getSingleResult();
            log.warn("聚合查询：{}", countResult);

            // --------------------------------- update ---------------------------------
            // 更新/删除要求事务内执行
            Transaction transaction = session.beginTransaction();

            CriteriaUpdate<Employee> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Employee.class);
            criteriaUpdate.from(Employee.class);
            criteriaUpdate.set("name", "zhangsan_new");
            criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), 1));
            int updateRows = session.createQuery(criteriaUpdate).executeUpdate();
            log.warn("更新：{}", updateRows);

            // --------------------------------- delete ---------------------------------
            CriteriaDelete<Employee> criteriaDelete = criteriaBuilder.createCriteriaDelete(Employee.class);
            criteriaDelete.from(Employee.class);
            criteriaDelete.where(criteriaBuilder.equal(root.get("id"), 2));
            int deleteRows = session.createQuery(criteriaDelete).executeUpdate();
            log.warn("删除：{}", deleteRows);

            transaction.commit();
        } finally {
            HibernateUtil.close();
        }
    }
}
