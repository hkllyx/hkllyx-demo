package com.hkllyx.demo.hibernate.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
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
@Entity // 实体注释
@Table(name = "department") // 显示映射数据库表名（采用隐式映射方式时可忽略）
@DynamicInsert // 动态插入，当字段为null的时候生成SQL时忽略
@DynamicUpdate // 动态更新，当字段为null的时候生成SQL时忽略
public class Department implements Serializable { // 实体等需要实现Serializable接口
    /** 主键 */
    @Id // 指定主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 指定主键生成方式
    @Column(name = "id") // 显示映射数据库字段（采用隐式映射方式时可忽略）
    private Long id;

    /** 编码 */
    @Basic  // 用于基础类型以及实现了java.io.Serializable的类型，可省缺
    private String code;

    /** 名称 */
    @Column(name = "name") // 指定命名策略之后可省缺
    private String name;

    /** 父部门 */
    @ManyToOne(fetch = FetchType.LAZY) // 部门多对一父部门
    @JoinColumn(name = "parent_id", referencedColumnName = "id") // 在@Column的基础上，指定联表查询参考字段
    private Department parent;

    /** 注册日期 */
    private LocalDate registerDate;

    /** 版本号 */
    @Version // 版本号，可用于乐观锁
    private Integer version;

    /** 管理员 */
    @ManyToMany // 部门多对多管理员
    @JoinTable(name = "department_manager", // 指定关联表名
            joinColumns = {@JoinColumn(name = "department_id", referencedColumnName = "id")}, // 指定当前表在关联表中的参考字段
            inverseJoinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "id")}) // 指定对方表（职员表）在关联表中的参考字段
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
                ", name='" + name + '\'' +
                ", parentId=" + (parent == null ? null : parent.id) +
                ", registerDate=" + registerDate +
                ", version=" + version +
                ", managers=" + managers +
                '}';
    }
}
