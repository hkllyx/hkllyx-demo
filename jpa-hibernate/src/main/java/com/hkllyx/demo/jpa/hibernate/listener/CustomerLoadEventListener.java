package com.hkllyx.demo.jpa.hibernate.listener;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.LoadEventListener;

/**
 * @author xiaoyong3
 * @date 2023/05/16
 */
@Slf4j
public class CustomerLoadEventListener implements LoadEventListener {

    @Override
    public void onLoad(LoadEvent event, LoadType loadType) throws HibernateException {
        log.warn("<<<<<< CustomerLoadEventListener onLoad >>>>>> entityId={}, entityClass={}, loadType={}",
                event.getEntityId(), event.getEntityClassName(), loadType);
    }
}
