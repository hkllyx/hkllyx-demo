package com.hkllyx.demo.basic.concurrent.others.simulation.carmaintaining;

import com.hkllyx.demo.basic.concurrent.util.ExecutorUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author HKLLY
 * @date 2019/4/12
 */
public class WaxAndMatic {
    private ExecutorService pool;

    public void waxAndMatic(Car car) {
        System.out.println("*********"
                + car.getClass().getSimpleName() + "*********");
        pool = ExecutorUtils.newFixedThreadPool(5);
        pool.execute(new Buff(car));
        pool.execute(new Wax(car));
    }

    public static void main(String[] args) throws InterruptedException {
        // 通过wait()，notifyAll()方法保证顺序
        WaxAndMatic test = new WaxAndMatic();
        test.waxAndMatic(new Car());
        TimeUnit.SECONDS.sleep(2);
        test.pool.shutdownNow();

        // 通过重入锁的Condition的signalAll()方法保证顺序
        test.waxAndMatic(new Car2());
        TimeUnit.SECONDS.sleep(2);
        test.pool.shutdownNow();
    }
}


