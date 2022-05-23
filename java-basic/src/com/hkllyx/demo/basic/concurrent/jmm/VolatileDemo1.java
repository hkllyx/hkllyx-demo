package com.hkllyx.demo.basic.concurrent.jmm;

/**
 * volatile 保证可见性演示
 *
 * @author HKLLY
 * @date 2019-08-18
 */
public class VolatileDemo1 {
    // boolean isInterrupted = false;
    volatile boolean isInterrupted = false;

    public void interrupt() {
        System.out.println("Interrupt task!");
        isInterrupted = true;
    }

    public void run() {
        System.out.println("Task started.");
        while (!isInterrupted) {
            //  do something...
        }
        System.out.println("Task finished.");
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileDemo1 demo = new VolatileDemo1();
        new Thread(() -> demo.run()).start();
        //  停顿一段时间，保证前面这个线程先运行。
        Thread.sleep(200);
        new Thread(() -> demo.interrupt()).start();
    }
}
