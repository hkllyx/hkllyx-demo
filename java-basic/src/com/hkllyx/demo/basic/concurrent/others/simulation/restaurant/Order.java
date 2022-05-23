package com.hkllyx.demo.basic.concurrent.others.simulation.restaurant;

/**
 * 订单
 *
 * @author HKLLY
 * @date 2019/4/14
 */
public class Order {
    private static int counter = 0;
    private final int id = counter++;
    /**
     * 生成订单的客户
     */
    private final Customer customer;
    /**
     * 处理订单的服务员
     */
    private final WaitPerson waitPerson;
    /**
     * 订单食物信息
     */
    private final Food food;

    public Order(Customer customer, WaitPerson waitPerson, Food food) {
        this.customer = customer;
        this.waitPerson = waitPerson;
        this.food = food;
    }

    public Customer getCustomer() {
        return customer;
    }

    public WaitPerson getWaitPerson() {
        return waitPerson;
    }

    public Food getFood() {
        return food;
    }

    @Override
    public String toString() {
        return "Order" + id + " item: " + food + " for: " +
                customer + " served by: " + waitPerson;
    }
}
