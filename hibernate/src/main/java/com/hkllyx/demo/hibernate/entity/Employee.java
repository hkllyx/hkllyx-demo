package com.hkllyx.demo.hibernate.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
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
@DynamicInsert
@DynamicUpdate
public class Employee implements Serializable {
    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 编号 */
    private String no;

    /** 名称 */
    private String name;

    /** 性别：0-男；1-女 */
    private Integer gender;

    /** 手机 */
    private String mobile;

    /** 邮箱 */
    private String email;

    /** 部门 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_code", referencedColumnName = "code")
    private Department department;

    /** 职位 */
    private String title;

    @Embedded // 引入Embeddable对象
    private EmployeeExtraInfo extraInfo;

    /** 版本号 */
    @Version // 版本号，可用于乐观锁
    private Integer version;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Employee employee = (Employee) o;

        if (!Objects.equals(id, employee.id))
            return false;
        return Objects.equals(no, employee.no);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (no != null ? no.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", no='" + no + '\'' +
                '}';
    }
}
