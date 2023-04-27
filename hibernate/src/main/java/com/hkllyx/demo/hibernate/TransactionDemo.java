package com.hkllyx.demo.hibernate;

import com.hkllyx.demo.hibernate.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Status;
import javax.transaction.Synchronization;

/**
 * 事务演示
 *
 * @author xiaoyong3
 * @date 2023/04/26
 */
@Slf4j
public class TransactionDemo {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.newSession()) {
            // 开启事务
            Transaction transaction = session.beginTransaction();
            Synchronization synchronization = new Synchronization() {
                @Override
                public void beforeCompletion() {
                    log.warn("事务完成前处理");
                }

                @Override
                public void afterCompletion(int status) {
                    if (Status.STATUS_COMMITTED == status) {
                        log.warn("事务已提交");
                    } else if (Status.STATUS_ROLLEDBACK == status) {
                        log.warn("事务已回滚");
                    }
                }
            };
            // 注册事务同步器，事务结束前后回调接口
            transaction.registerSynchronization(synchronization);
            // 设置事务超时时间，单位：秒
            transaction.setTimeout(10);
            try {
                // session.delete();
                // 提交事务
                transaction.commit();
            } catch (Exception e) {
                // 出现异常后如果事务还存活，回滚事务
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            }
        } finally {
            HibernateUtil.close();
        }
    }
}
