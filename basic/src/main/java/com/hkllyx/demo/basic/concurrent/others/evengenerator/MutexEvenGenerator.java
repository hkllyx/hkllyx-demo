package com.hkllyx.demo.basic.concurrent.others.evengenerator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用重入锁进行执行控制
 *
 * @author HKLLY
 * @date 2019/4/11
 */
public class MutexEvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    private final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        EvenChecker.test(new MutexEvenGenerator(), 10);
    }

    @Override
    public synchronized int next() {
        lock.lock();
        try {
            currentEvenValue++;
            // Cause failure faster.
            Thread.yield();
            currentEvenValue++;
            return currentEvenValue;
        } finally {
            lock.unlock();
        }
    }
}
