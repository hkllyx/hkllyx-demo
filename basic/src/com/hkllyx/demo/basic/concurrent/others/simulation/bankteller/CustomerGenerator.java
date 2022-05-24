package com.hkllyx.demo.basic.concurrent.others.simulation.bankteller;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 客户生成器
 *
 * @author HKLLY
 * @date 2019/4/14
 */
public class CustomerGenerator implements Runnable {
    private static Random random = new Random(47);
    private CustomerLine customers;

    public CustomerGenerator(CustomerLine customers) {
        this.customers = customers;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(300));
                customers.add(new Customer(random.nextInt(1000)));
            }
        } catch (InterruptedException e) {
            System.out.println("CustomerGenerator Interrupted");
        }
        System.out.println("CustomerGenerator Terminating");
    }
}
