package com.hkllyx.demo.basic.concurrent.atomic;

import com.hkllyx.demo.basic.concurrent.util.ExecutorUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * int/Integer的原子性包装类型<br>
 * 同理还有：AtomicBoolean、AtomicLong、AtomicIntegerArray、AtomicLongArray
 *
 * @author HKLLY
 * @date 2019-07-11
 */
public class AtomicIntegerDemo {
    private static int money = 100000;
    private static AtomicInteger aMoney = new AtomicInteger(money);

    public static void main(String[] args) throws InterruptedException {
        int steps = money;
        ExecutorService pool = ExecutorUtils.newCachedThreadPool();
        for (int i = 0; i < steps; i++) {
            pool.execute(() -> aMoney.decrementAndGet());
        }
        for (int i = 0; i < steps; i++) {
            pool.execute(() -> money--);
        }
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("At the end aMoney is: " + aMoney);
        System.out.println("At the end money is: " + money);
        pool.shutdown();
    }
}
