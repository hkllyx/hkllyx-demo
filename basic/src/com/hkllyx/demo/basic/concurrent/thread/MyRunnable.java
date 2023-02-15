package com.hkllyx.demo.basic.concurrent.thread;

/**
 * @author HKLLY
 * @date 2019-08-21
 */
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("do something");
    }

    public static void main(String[] args) {
        new Thread(new MyRunnable()).start();
    }
}
