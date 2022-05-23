package com.hkllyx.demo.basic.concurrent.synchronizer.semaphore;

import java.util.concurrent.TimeUnit;

/**
 * 签出对象任务
 *
 * @author HKLLY
 * @date 2019/4/14
 */
public class CheckOutTask<T> implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final ItemPool<T> pool;

    public CheckOutTask(ItemPool<T> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            //  获取对象
            T item = pool.checkOut();
            System.out.println(this + " 签出了：" + item);
            TimeUnit.SECONDS.sleep(1);
            //  签入对象
            pool.checkIn(item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "CheckOutTask " + id;
    }
}
