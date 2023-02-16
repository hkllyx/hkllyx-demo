package com.hkllyx.demo.basic.concurrent.synchronizer.countdownlatch;

/**
 * @author HKLLY
 * @date 2019-08-21
 */

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        new Driver(10).start();
    }
}

