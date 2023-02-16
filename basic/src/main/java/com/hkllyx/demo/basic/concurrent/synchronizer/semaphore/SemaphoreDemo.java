package com.hkllyx.demo.basic.concurrent.synchronizer.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author HKLLY
 * @date 2019/4/14
 */
public class SemaphoreDemo {
    private static final int SIZE = 25;

    public static void main(String[] args) throws InterruptedException {
        final ItemPool<Item> pool = new ItemPool<>(Item.class, SIZE);
        ExecutorService exec = Executors.newCachedThreadPool();
        // 执行签出任务
        for (int i = 0; i < SIZE; i++) {
            exec.execute(new CheckOutTask<>(pool));
        }
        TimeUnit.MILLISECONDS.sleep(20);
        System.out.println("签出任务全部开始");
        // 创建 Item 列表
        List<Item> list = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            Item item = pool.checkOut();
            System.out.println("主线程签出了：" + item);
            // 放入list
            list.add(item);
        }
        //  主线程用完了所有信号量许可，再想签出时被阻塞
        Future<?> blocked = exec.submit(() -> {
            try {
                pool.checkOut();
            } catch (InterruptedException e) {
                System.out.println("阻塞线程被取消");
            }
        });
        TimeUnit.SECONDS.sleep(1);
        //  取消阻塞的线程
        blocked.cancel(true);
        // 签入list中的Item
        System.out.println("开始签入");
        for (Item i : list) {
            pool.checkIn(i);
        }
        // 冗余的签入会被忽略.
        for (Item i : list) {
            pool.checkIn(i);
        }
        exec.shutdownNow();
    }
}
