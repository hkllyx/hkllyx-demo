package com.hkllyx.demo.basic.concurrent.others.simulation.restaurant;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author HKLLY
 * @date 2019/4/14
 */
public class WaitPerson implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    /**
     * 服务的餐厅
     */
    private Restaurant restaurant;
    /**
     * 完成的订单（送餐）列队
     */
    BlockingQueue<Plate> filledOrders = new LinkedBlockingQueue<>();

    public WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void placeOrder(Customer customer, Food food) {
        try {
            restaurant.orders.put(new Order(customer, this, food));
        } catch (InterruptedException e) {
            System.out.println(this + " place order interrupted");
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 取出完成列队中的餐点
                Plate plate = filledOrders.take();
                // 送给对应的客户
                System.out.println(this + " received " + plate +
                        " delivered to " + plate.getOrder().getCustomer());
                plate.getOrder().getCustomer().delivered(plate);
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
        System.out.println(this + " off duty");
    }

    @Override
    public String toString() {
        return "WaitPerson" + id;
    }
}
