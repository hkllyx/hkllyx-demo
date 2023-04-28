package com.hkllyx.demo.jpa.hibernate;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 增删改查
 * @author xiaoyong3
 * @date 2023/04/28
 */
@Slf4j
public class _4PersitTest {
    
    @Test
    void test() {
        // // 获取数据，立即去缓存或数据库，返回的是真正的entity实例
        // Department parentDepartment = session.get(Department.class, 1L);
        // log.warn("获取数据（get）：class={}，data={}", parentDepartment.getClass(), parentDepartment);
        //
        // Department department3 = new Department();
        // department3.setCode("D003");
        // department3.setName("IT部");
        // department3.setParent(parentDepartment);
        // department3.setRegisterDate(LocalDate.now());
        // // 持久化数据，立即填充id并保存到数据库
        // Serializable id = session.save(department3);
        // log.warn("持久化数据（save）：id={}", id);
        //
        // // 开启事务事务
        // Transaction transaction = session.beginTransaction();
        // Department department4 = new Department();
        // department4.setCode("D004");
        // department4.setName("人力资源部");
        // department4.setParent(parentDepartment);
        // department4.setRegisterDate(LocalDate.now());
        // // 持久化数据，并不保证立即填充id并保存到数据库，可能要在flush后才执行这些操作
        // session.persist(department4);
        // log.warn("持久化数据（persist）后&flush前：{}", department4);
        //
        // // 强制冲刷session数据。事务结束前会自动冲刷
        // session.flush();
        // log.warn("flush后：{}", department4);
        // transaction.commit();
        //
        // // 加载数据。load的数据被认为是一定存在的，如果数据不存在会抛出异常
        // Employee employee = session.load(Employee.class, 1L);
        // // 在employee使用前并不会立即去加载，得到的一个Hibernate代理对象
        // log.warn("employee使用前：type={}", employee.getClass());
        // // 使用时采取加载。注意，getId操作也不会真正得去加载
        // String no = employee.getNo();
        // log.warn("employee使用后，no={}", no);
        //
        // // 因为之前使用load加载过数据，所以get的是缓存中代理对象
        // employee = session.get(Employee.class, 1L);
        // log.warn("再次获取数据（get）：type={}，data={}", employee.getClass(), employee);
        //
        // employee = session.find(Employee.class, 2L);
        // log.warn("查询数据（find）：class={}，data={}", employee.getClass(), employee);
        //
        // // 删除，remove内部调用delete方法，基本等效
        // session.remove(employee);
        // // session.delete(employee);
        // log.warn("删除数据：{}", employee);
        //
        // try {
        //     // 不存在，get返回的是null
        //     employee = session.get(Employee.class, -1L);
        //     log.warn("获取不存在的数据（get）：{}", employee);
        // } catch (Exception e) {
        //     log.error("获取不存在的数据（get）：{}", e.getClass());
        // }
        //
        // try {
        //     // 不存在，load抛出ObjectNotFoundException
        //     employee = session.load(Employee.class, -1L);
        //     employee.getNo();
        // } catch (Exception e) {
        //     log.error("加载不存在的数据（load）：{}", e.getClass());
        // }
    }
}
