package com.hkllyx.demo.hibernate.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * 部门
 *
 * @author xiaoyong3
 * @date 2023/04/11
 */
@Getter
@Setter
@Entity
public class Department {
    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** 编码 */
    private String code;

    /** 名称 */
    private String name;

    /** 父部门编码 */
    @ManyToOne
    private Department parentDepartment;

    /** 部长编码 */
    @ManyToOne
    @JoinColumn(name = "director_code", referencedColumnName = "code")
    private Employee director;

    /** 注册日期 */
    private LocalDate registerDate;

    /** 版本号 */
    @Version
    private Integer version;

    /** 管理员 */
    @ManyToMany
    @JoinTable(name = "ref_department_manager",
            joinColumns = {@JoinColumn(name = "department_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "id")})
    @ToString.Exclude
    private List<Employee> managers;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Department that = (Department) o;

        if (!Objects.equals(id, that.id))
            return false;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", code='" + code + '\'' +
                '}';
    }
}
