package com.hkllyx.demo.jpa.hibernate;

import com.hkllyx.demo.jpa.hibernate.entity.Department;
import com.hkllyx.demo.jpa.hibernate.entity.Employee;
import com.hkllyx.demo.jpa.hibernate.util.JPAUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *     {@link org.hibernate.Session}API和{@link javax.persistence.EntityManager}API都是处理持久化数据的一个上下文，
 *     既持久化上下文。持久化数据有一个关联持久化上下文和底层数据的特定状态。<br>
 *     <ol>
 *         <li>transient：实体只是被初始化，但没有和持久化上下文关联。在数据库也没有持久化体现，
 *         并且一般情况下标识符值没有被赋值（既ID，如果使用了assigned生成器则可能被赋值）</li>
 *         <li>managed/persistent：实体有标识符且关联了持久化上下文，但不一定物理地保存到数据库</li>
 *         <li>detached：实体有标识符但不再关联持久化上下文。通常是因为持久化上下文被关闭了，或者是实体被从上下文中移除了</li>
 *         <li>removed：实体有标识符且关联了持久化上下文。但是，该实体计划被数据库移除</li>
 *     </ol>
 *     {@link org.hibernate.Session}和{@link javax.persistence.EntityManager}的绝大多数操作都是将实体在这些状态中转移。
 * </p>
 *
 * @author xiaoyong3
 * @date 2023/04/28
 */
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class _4PersistenceContextTest {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static SessionFactory sessionFactory;
    private static Session session;

    @BeforeAll
    static void beforeAll() {
        entityManagerFactory = JPAUtil.defualtEntityManagerFactory();
        entityManager = entityManagerFactory.createEntityManager();
        sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        session = entityManager.unwrap(Session.class);
    }

    @AfterAll
    static void afterAll() {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    @Order(100)
    void obtain() {
        // 懒加载，获取到的是实体的代理类实例
        Department department1 = entityManager.getReference(Department.class, 1L);
        log.warn("仅getReference后：不会立即执行SQL，class={}", department1.getClass());
        // 使用时真正地获取数据（Department#toString方法中使用）
        log.warn("getReference&使用后：执行SQL并获取数据，data={}", department1);

        // find是立马加载，获取到是实体类本身实例
        Department department2 = entityManager.find(Department.class, 2L);
        log.warn("find后：立即执行SQL，class={}", department2.getClass());

        department1 = entityManager.find(Department.class, 1L);
        log.warn("再次find后：直接从持久化上下文中读取数据，class={}，data={}", department1.getClass(), department1);

        department2 = entityManager.find(Department.class, 2L);
        log.warn("再次find后：直接从持久化上下文中读取数据，class={}，data={}", department2.getClass(), department2);
    }

    @Test
    @Order(101)
    void obtainByNativeAPI() {
        // 懒加载，获取到的是实体的代理类实例
        Employee employee1 = session.load(Employee.class, 1L);
        log.warn("仅load后：不会立即执行SQL，class={}", employee1.getClass());
        // 使用时真正地获取数据（Department#toString方法中使用）
        log.warn("load&使用后：执行SQL并获取数据，data={}", employee1);

        // get是立马加载，获取到是实体类本身实例
        Employee employee2 = session.get(Employee.class, 2L);
        log.warn("get后：立即执行SQL，class={}", employee2.getClass());
    }

    @Test
    @Order(102)
    void obtainNotExitsEntity() {
        // 懒加载，获取到的是实体的代理类实例
        Department department = entityManager.getReference(Department.class, -1L);
        log.warn("仅getReference后：不会立即执行SQL，class={}", department.getClass());
        // 不存在抛出EntityNotFoundException异常
        Assertions.assertThrows(EntityNotFoundException.class, department::toString);

        // 因为持久化上下文中没有该数据，所以还是会执行查询SQL
        department = entityManager.find(Department.class, -1L);
        log.warn("find不存在的实体后：data={}", department);
    }

    @Test
    @Order(103)
    void obtainByMultipleIds() {
        // 立马加载
        List<Department> departments = session.byMultipleIds(Department.class).multiLoad(1L, 2L);
        log.warn("byMultipleIds：data={}", departments);
    }

    @Test
    @Order(103)
    void obtainByNatualId() {
        // 立马加载
        Department department = session.bySimpleNaturalId(Department.class).load("D001");
        log.warn("bySimpleNaturalId： class={}，data={}", department.getClass(), department);

        // 另一种方式，但会从持久化上下文中获取
        department = session.byNaturalId(Department.class).using("code", "D001").load();
        log.warn("byNaturalId： class={}，data={}", department.getClass(), department);
    }

    @Test
    @Order(200)
    void persist() {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Department department = new Department();
            department.setCode("D003");
            department.setName("IT部");
            department.setRegisterDate(LocalDate.now());
            // JPA理论上不保证立即填充id并保存到数据库，可能要在flush后才执行这些操作
            // 但在Hibernate中，也是立即保存的
            entityManager.persist(department);
            log.warn("persist后，可以看到insert语句已执行");

            // commit隐式flush
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive() || transaction.getRollbackOnly()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Test
    @Order(201)
    void persistByNativeAPI() {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Department department = new Department();
            department.setCode("D004");
            department.setName("人力资源部");
            department.setRegisterDate(LocalDate.now());
            // 立即填充id并保存到数据库
            Serializable id = session.save(department);
            log.warn("save后：id={}，data={}", id, department);

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive() || transaction.getRollbackOnly()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Test
    @Order(300)
    void modify() {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Department department = entityManager.find(Department.class, 1L);
            department.setName("营销部-NEW");
            log.warn("变更属性后：data={}", department);

            // 隐式flush后会更新数据库
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive() || transaction.getRollbackOnly()) {
                transaction.rollback();
            }
            throw e;
        }

        Department department = entityManager.find(Department.class, 1L);
        log.warn("变更属性后重新find: data={}", department);
    }

    @Test
    @Order(400)
    void refresh() {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Employee employee = entityManager.find(Employee.class, 1L);
            entityManager.createQuery("update Employee set name = UPPER(name) where id = 1").executeUpdate();
            log.warn("refresh前: data={}", employee);

            entityManager.refresh(employee);
            log.warn("refresh后: data={}", employee);

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive() || transaction.getRollbackOnly()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Test
    @Order(500)
    void remove() {
        entityManager.clear();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Department department = entityManager.find(Department.class, 1L);
            entityManager.remove(department);
            log.warn("remove后");

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive() || transaction.getRollbackOnly()) {
                transaction.rollback();
            }
            throw e;
        }

        Department department = entityManager.find(Department.class, 1L);
        log.warn("remove后重新find: data={}", department);
    }

    @Test
    @Order(501)
    void removeDetachedInstance() {
        entityManager.clear();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Department department = new Department();
            department.setId(2L);
            // 没有version不会执行删除
            department.setVersion(0);
            // 不能删除detached的实例（持久化上下文中没有这个实例）
            // java.lang.IllegalArgumentException: Removing a detached instance com.hkllyx.demo.jpa.hibernate.entity.Department#1
            Assertions.assertThrows(IllegalArgumentException.class, () -> entityManager.remove(department));
            log.warn("remove后");

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive() || transaction.getRollbackOnly()) {
                transaction.rollback();
            }
            throw e;
        }

        Department department = entityManager.find(Department.class, 1L);
        log.warn("remove后重新find: data={}", department);
    }
}
