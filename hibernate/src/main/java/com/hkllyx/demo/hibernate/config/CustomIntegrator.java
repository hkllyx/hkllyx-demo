package com.hkllyx.demo.hibernate.config;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

/**
 * 配置Integrator。Integrator最大的作用就是注册事务监听器。在构建SessionFactory时被整合。
 * <p>
 * 通过SPI注册。
 *
 * @author xiaoyong3
 * @date 2023/04/27
 */
@Slf4j
public class CustomIntegrator implements Integrator {

    @Override
    public void integrate(Metadata metadata,
                          SessionFactoryImplementor sessionFactory,
                          SessionFactoryServiceRegistry serviceRegistry) {
        log.warn("<<<<<< CustomIntegrator已运行！ >>>>>>");

        // EventListenerRegistry是负责事件监听器注册的服务，所以可以使用serviceRegistry来寻找
        EventListenerRegistry eventListenerRegistry = serviceRegistry.getService(EventListenerRegistry.class);

        // DuplicationStrategy用于判断两个时间监听器是否相同，并且在出现重复注册时决定采取什么措施（DuplicationStrategy.Action）
        // eventListenerRegistry.addDuplicationStrategy(new CustomDuplicationStrategy());

        // 三种注册监听器的方式：
        // 1) 覆盖已注册的监听器
        // eventListenerRegistry.setListeners(EventType.AUTO_FLUSH, DefaultAutoFlushEventListener.class);
        // 2) 注册监听器到监听器链开头
        // eventListenerRegistry.prependListeners(EventType.PERSIST, DefaultPersistEventListener.class);
        // 3) 注册监听器到监听器链末尾
        // eventListenerRegistry.appendListeners(EventType.MERGE, DefaultMergeEventListener.class);
    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory,
                             SessionFactoryServiceRegistry serviceRegistry) {
    }
}
