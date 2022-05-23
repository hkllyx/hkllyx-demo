package com.hkllyx.demo.basic.concurrent.jmm;

/**
 * volatile 不保证原子性演示
 *
 * @author HKLLY
 * @date 2019-08-18
 */
public class VolatileDemo2 {
    volatile int a = 0;

    public void increase() {
        a++;
    }

    public static void main(String[] args) {
        VolatileDemo2 demo = new VolatileDemo2();

        int c = Thread.activeCount();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    demo.increase();
                }
            }).start();
        }

        //  保证自定义线程执行完成
        while (Thread.activeCount() > c) {
            Thread.yield();
        }
        //  期望值为 10 * 1000 = 10000
        System.out.println("a = " + demo.a);
    }
}
