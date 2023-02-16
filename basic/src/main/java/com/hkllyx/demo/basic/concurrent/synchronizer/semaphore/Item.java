package com.hkllyx.demo.basic.concurrent.synchronizer.semaphore;

/**
 * @author HKLLY
 * @date 2019-08-22
 */
public class Item {
    private static int counter = 0;
    private final int id = counter++;

    @Override
    public String toString() {
        return "Item{id=" + id + '}';
    }
}
