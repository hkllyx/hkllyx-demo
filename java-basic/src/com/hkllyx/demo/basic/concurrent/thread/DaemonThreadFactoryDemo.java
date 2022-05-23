package com.hkllyx.demo.basic.concurrent.thread;

import java.util.concurrent.ThreadFactory;

/**
 * @author HKLLY
 * @date 2019-07-11
 */
public class DaemonThreadFactoryDemo implements ThreadFactory {

    @Override
    public Thread newThread(Runnable task) {
        return new Thread(task);
    }

    public static void main(String[] args) {
        ThreadFactory factory = new DaemonThreadFactoryDemo();
        factory.newThread(new MyThread()).start();
    }
}
