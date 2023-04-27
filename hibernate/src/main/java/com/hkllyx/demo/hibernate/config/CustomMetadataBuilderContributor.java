package com.hkllyx.demo.hibernate.config;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;

/**
 * Hibernate原生启动方式可以通过Metadata配置多种策略，
 * 但是采用JPA启动方式时，EntityManagerFactory代替了SessionFactory，不会直接使用到Metadata对象。
 * <p>
 * 这时可以实现MetadataBuilderContributor接口进行配置，需要在persistence.xml配置文件通过
 * hibernate.metadata_builder_contributor（value为MetadataBuilderContributor的完全限定名）配置。
 * <p>
 * 同理，还有其他启动配置项配置，例如：hibernate.integrator_provider、hibernate.type_contributors
 *
 * @author xiaoyong3
 * @date 2023/04/27
 * @see EntityManagerFactoryBuilderImpl
 */
@Slf4j
public class CustomMetadataBuilderContributor implements MetadataBuilderContributor {

    /**
     * @param metadataBuilder The {@link MetadataBuilder}, to which to contribute.
     * @see EntityManagerFactoryBuilderImpl#applyMetadataBuilderContributor
     */
    @Override
    public void contribute(MetadataBuilder metadataBuilder) {
        log.warn("<<<<<< CustomMetadataBuilderContributor已运行！ >>>>>>");

        // 通过MetadataBuilder配置Metadata的其他属性，如命名策略、基础类型映射、属性转换器等
    }
}
