package com.hkllyx.demo.basic.concurrent.others.simulation.restaurant;

import java.util.concurrent.SynchronousQueue;

/**
 * @author HKLLY
 * @date 2019/4/14
 */
public class Customer implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    /**
     * 服务员
     */
    private final WaitPerson waitPerson;
    /**
     * 已经上了的餐点
     */
    private SynchronousQueue<Plate> placeSetting = new SynchronousQueue<>();

    public Customer(WaitPerson waitPerson) {
        this.waitPerson = waitPerson;
    }

    /**
     * 被送餐
     *
     * @param plate 餐点
     * @throws InterruptedException ex
     */
    public void delivered(Plate plate) throws InterruptedException {
        placeSetting.put(plate);
    }

    @Override
    public void run() {
        // 点餐
        for (Course course : Course.values()) {
            Food food = course.randomSelection();
            // 服务员将订单添加餐厅的订单列队中
            waitPerson.placeOrder(this, food);
            try {
                // 客户食用食物
                System.out.println(this + " eating " + placeSetting.take());
            } catch (InterruptedException e) {
                // 等待上餐
                System.out.println(this + " waiting for " + food + " interrupted");
            }
        }
        System.out.println(this + " finished meal, leaving");
    }

    @Override
    public String toString() {
        return "Customer" + id;
    }
}
