package com.hkllyx.demo.basic.concurrent.others.simulation.carmaintaining;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author HKLLY
 * @date 2019/4/12
 */
public class Car2 extends Car {
    private boolean waxOn = false;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    @Override
    public void waxed() {
        // 加锁
        lock.lock();
        try {
            // 设为已打蜡
            waxOn = true;
            // 唤醒Buff线程
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void buffed() {
        lock.lock();
        try {
            waxOn = false;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void waitingForWaxing() throws InterruptedException {
        lock.lock();
        try {
            while (!waxOn) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void waitingForBuffing() throws InterruptedException {
        lock.lock();
        try {
            while (waxOn) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }
}
