package com.hkllyx.demo.basic.concurrent.others.simulation.bankteller;

import com.hkllyx.demo.basic.concurrent.util.ExecutorUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author HKLLY
 * @date 2019/4/14
 */
public class BankTellerSimulation {
    /**
     * 最大客户线数量
     */
    private static final int MAX_LINE_SIZE = 50;
    /**
     * 调整出纳员数量周期
     */
    private static final int ADJUSTMENT_PERIOD = 1000;

    public static void main(String[] args) throws InterruptedException {
        CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
        ExecutorService pool = ExecutorUtils.newCachedThreadPool();
        pool.execute(new CustomerGenerator(customers));
        pool.execute(new TellerManager(pool, customers, ADJUSTMENT_PERIOD));
        TimeUnit.SECONDS.sleep(5);
        pool.shutdownNow();
    }
}
