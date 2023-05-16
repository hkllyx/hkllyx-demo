package com.hkllyx.demo.jpa.hibernate.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author xiaoyong3
 * @date 2023/05/16
 */
@Slf4j
public class CustomInterceptor extends EmptyInterceptor {

    @Override
    public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        log.warn("<<<<<< CustomInterceptor onLoad >>>>>> entity={}, id={}, state={}",
                entity, id, Arrays.toString(state));
        return super.onLoad(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
                                String[] propertyNames, Type[] types) {
        log.warn("<<<<<< CustomInterceptor onFlushDirty >>>>>> entity={}, id={}, currentState={}, previousState={}",
                entity, id, Arrays.toString(currentState), Arrays.toString(previousState));
        return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        log.warn("<<<<<< CustomInterceptor onSave >>>>>> entity={}, id={}, state={}",
                entity, id, Arrays.toString(state));
        return super.onSave(entity, id, state, propertyNames, types);
    }

    @Override
    public void afterTransactionBegin(Transaction tx) {
        log.warn("<<<<<< CustomInterceptor afterTransactionBegin >>>>>> tx status={}", tx.getStatus());
        super.afterTransactionBegin(tx);
    }

    @Override
    public void beforeTransactionCompletion(Transaction tx) {
        log.warn("<<<<<< CustomInterceptor beforeTransactionCompletion >>>>>> tx status={}", tx.getStatus());
        super.beforeTransactionCompletion(tx);
    }

    @Override
    public void afterTransactionCompletion(Transaction tx) {
        log.warn("<<<<<< CustomInterceptor afterTransactionCompletion >>>>>> tx status={}", tx.getStatus());
        super.afterTransactionCompletion(tx);
    }

    @Override
    public String onPrepareStatement(String sql) {
        log.warn("<<<<<< CustomInterceptor onPrepareStatement >>>>>> sql={}", sql);
        return super.onPrepareStatement(sql);
    }
}
