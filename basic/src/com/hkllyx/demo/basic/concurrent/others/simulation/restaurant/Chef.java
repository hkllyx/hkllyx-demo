package com.hkllyx.demo.basic.concurrent.others.simulation.restaurant;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 主厨
 *
 * @author HKLLY
 * @date 2019/4/14
 */
public class Chef implements Runnable {
    private static Random random = new Random(47);
    private static int counter = 0;
    private final int id = counter++;
    /**
     * 服务的餐厅
     */
    private Restaurant restaurant;

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 从餐厅的订单列队取出订单
                Order order = restaurant.orders.take();
                // 生产订单上的食物
                Food requestedItem = order.getFood();
                TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
                // 将食物放入盘中，并放入相应服务员的完成列队中
                Plate plate = new Plate(order, requestedItem);
                order.getWaitPerson().filledOrders.put(plate);
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
        System.out.println(this + " duty");
    }

    @Override
    public String toString() {
        return "Chef" + id;
    }
}
