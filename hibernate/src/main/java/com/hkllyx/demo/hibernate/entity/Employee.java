package com.hkllyx.demo.hibernate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * 职员
 *
 * @author xiaoyong3
 * @date 2023/04/11
 */
@Getter
@Setter
@Entity
public class Employee {
    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** 编码 */
    private String code;

    /** 名称 */
    private String name;

    /** 性别：0-男；1-女 */
    private Integer gender;

    /** 出生日期 */
    private LocalDate birth;

    /** 手机 */
    private String mobile;

    /** 邮箱 */
    private String email;

    /** 部门 */
    @ManyToOne
    @JoinColumn(name = "department_code", referencedColumnName = "code")
    private Department department;

    /** 职位 */
    private String title;

    /** 薪资 */
    private BigDecimal salary;

    /** 入职日期 */
    private LocalDate onboardDate;

    /** 离职日期 */
    private LocalDate departureDate;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Employee employee = (Employee) o;

        if (!Objects.equals(id, employee.id))
            return false;
        return Objects.equals(code, employee.code);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", code='" + code + '\'' +
                '}';
    }
}
