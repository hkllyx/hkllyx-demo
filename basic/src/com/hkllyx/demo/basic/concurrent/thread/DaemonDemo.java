package com.hkllyx.demo.basic.concurrent.thread;

/**
 * 守护线程是程序运行时在后台提供服务的线程，不属于程序中不可或缺的部分。
 * 当所有非守护线程结束时，程序也就终止，同时会杀死所有守护线程。
 * 主线程属于非守护线程。
 *
 * @author HKLLY
 * @date 2019/4/10
 */
public class DaemonDemo implements Runnable {
    private static int counter = 0;
    private int id = counter++;

    @Override
    public void run() {
        while (true) {
            System.out.println("守护线程执行中");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread daemon = new Thread(new DaemonDemo());
        daemon.setDaemon(true);
        daemon.start();
        Thread.sleep(10);
        // 退出主线程，因为调度器的时间不是非常精确，所以守护线程肯不会立即停止
        System.out.println("------------------退出主线程----------------");
    }
}
