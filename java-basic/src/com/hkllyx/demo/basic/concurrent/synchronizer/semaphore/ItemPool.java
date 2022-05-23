package com.hkllyx.demo.basic.concurrent.synchronizer.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 将一个类的对象使用 Semaphore 和 ArrayList 包装成可签出的对象的对象池
 *
 * @author HKLLY
 * @date 2019/4/14
 */
public class ItemPool<T> {
    /**
     * 信号量size
     */
    private int size;
    /**
     * 对象列表
     */
    private List<T> itemList = new ArrayList<>();
    /**
     * 对象是否被签出
     */
    private volatile boolean[] checkedOut;
    /**
     * 可用信号量
     */
    private Semaphore available;

    public ItemPool(Class<T> clazz, int size) {
        this.size = size;
        checkedOut = new boolean[size];
        available = new Semaphore(size, true);
        for (int i = 0; i < size; i++) {
            try {
                // 将对象保存到list
                itemList.add(clazz.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 签出对象
     */
    public T checkOut() throws InterruptedException {
        // 获取信号量许可
        available.acquire();
        // 签对象
        synchronized (ItemPool.class) {
            for (int i = 0; i < size; i++) {
                // 签出第一个没有签出的对象
                if (!checkedOut[i]) {
                    checkedOut[i] = true;
                    return itemList.get(i);
                }
            }
        }
        return null;
    }

    /**
     * 签入对象
     */
    public void checkIn(T task) {
        //  签入对象
        boolean isCheckedIn = false;
        synchronized (ItemPool.class) {
            int index = itemList.indexOf(task);
            if (index != -1 && checkedOut[index]) {
                checkedOut[index] = false;
                isCheckedIn = true;
            }
        }
        //  是否信号量许可
        if (isCheckedIn) {
            available.release();
        }
    }
}
