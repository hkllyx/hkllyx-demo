package com.hkllyx.demo.basic.concurrent.others.performancetuning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author HKLLY
 * @date 2019/4/15
 */
public class ReaderWriterList<T> {
    private ArrayList<T> lockList;
    /**
     * 重入读写锁（读不加锁，写加锁）
     */
    private ReentrantReadWriteLock lock =
            new ReentrantReadWriteLock(true);

    public ReaderWriterList(int size, T initialValue) {
        lockList = new ArrayList<>
                (Collections.nCopies(size, initialValue));
    }

    public T set(int index, T element) {
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            return lockList.set(index, element);
        } finally {
            writeLock.unlock();
        }
    }

    public T get(int index) {
        Lock readLock = lock.readLock();
        readLock.lock();
        try {
            if (lock.getReadLockCount() > 1) {
                System.out.print(lock.getReadLockCount() + ", ");
            }
            return lockList.get(index);
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        new ReaderWriterListTest(30, 1);
    }
}
