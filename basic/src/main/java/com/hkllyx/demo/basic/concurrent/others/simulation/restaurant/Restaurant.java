package com.hkllyx.demo.basic.concurrent.others.simulation.restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 餐厅
 *
 * @author HKLLY
 * @date 2019/4/14
 */
public class Restaurant implements Runnable {
    /**
     * 餐厅中产生的订单
     */
    BlockingQueue<Order> orders = new LinkedBlockingQueue<>();
    private Random random = new Random(47);
    private List<WaitPerson> waitPeople = new ArrayList<>();
    private List<Chef> chefs = new ArrayList<>();
    private ExecutorService pool;

    public Restaurant(ExecutorService pool, int nWaitPeople, int nChefs) {
        this.pool = pool;
        for (int i = 0; i < nWaitPeople; i++) {
            WaitPerson waitPerson = new WaitPerson(this);
            waitPeople.add(waitPerson);
            pool.execute(waitPerson);
        }
        for (int i = 0; i < nChefs; i++) {
            Chef chef = new Chef(this);
            chefs.add(chef);
            pool.execute(chef);
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 服务员接待客户
                WaitPerson wp = waitPeople.get(random.nextInt(waitPeople.size()));
                Customer c = new Customer(wp);
                // 用户点餐
                pool.execute(c);
                TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
        System.out.println(this + " closed");
    }

    @Override
    public String toString() {
        return "Restaurant";
    }
}
