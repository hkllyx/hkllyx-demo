package com.hkllyx.demo.basic.concurrent.others.simulation.restaurant;

import com.hkllyx.demo.basic.concurrent.util.ExecutorUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 模拟餐厅
 *
 * @author HKLLY
 * @date 2019/4/14
 */
public class RestaurantWithQueuesDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = ExecutorUtils.newCachedThreadPool();
        // 餐厅对象
        Restaurant restaurant = new Restaurant(pool, 1, 1);
        // 运行餐厅
        pool.execute(restaurant);
        TimeUnit.MILLISECONDS.sleep(1000);
        pool.shutdown();
    }
}
