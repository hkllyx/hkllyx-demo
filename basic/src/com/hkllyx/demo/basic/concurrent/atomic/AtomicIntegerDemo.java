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
    static int money = 100000;
    static volatile int vMoney = money;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger aMoney = new AtomicInteger(money);
        ExecutorService pool = ExecutorUtils.newCachedThreadPool();
        int steps = money;
        for (int i = 0; i < steps; i++) {
            pool.execute(() -> money--);
            pool.execute(() -> vMoney--);
            pool.execute(() -> aMoney.decrementAndGet());
        }
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("At the end money is: " + money);
        System.out.println("At the end vMoney is: " + vMoney);
        System.out.println("At the end aMoney is: " + aMoney);
        pool.shutdown();
    }
}
