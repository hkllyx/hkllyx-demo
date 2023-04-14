package com.hkllyx.demo.hibernate.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 联系方式
 *
 * @author xiaoyong3
 * @date 2023/04/11
 */
@Getter
@Setter
@Embeddable
@DynamicInsert
@DynamicUpdate
public class EmployeeExtraInfo {
    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
