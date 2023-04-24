package com.hkllyx.demo.hibernate.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 职员额外信息
 *
 * @author xiaoyong3
 * @date 2023/04/11
 */
@Getter
@Setter
@Embeddable // 没有单独的表，不能脱离主表查询，也不能使用@Id注解
@DynamicInsert
@DynamicUpdate
public class EmployeeExtraInfo implements Serializable {
    /** 出生日期 */
    private LocalDate birth;

    /** 个人简介 */
    private String introduction;

    /** 博客地址 */
    private String blogUri;

    /** 当前薪资 */
    private BigDecimal salary;

    /** 入职日期 */
    private LocalDate onboardDate;

    /** 离职日期 */
    private LocalDate departureDate;
}
