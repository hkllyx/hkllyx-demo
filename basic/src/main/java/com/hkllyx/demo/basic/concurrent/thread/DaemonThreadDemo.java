package com.hkllyx.demo.basic.concurrent.thread;

/**
 * 守护线程是程序运行时在后台提供服务的线程，不属于程序中不可或缺的部分。
 * 当所有非守护线程结束时，程序也就终止，同时会杀死所有守护线程。
 * 主线程属于非守护线程。
 *
 * @author HKLLY
 * @date 2019/4/10
 */
public class DaemonThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        DaemonThreadFactory factory = new DaemonThreadFactory();
        factory.newThread(() -> {
            while (true) {
                System.out.println("守护线程执行中");
            }
        }).start();
        Thread.sleep(10);
        // 退出主线程，因为调度器的时间不是非常精确，所以守护线程肯不会立即停止
        System.out.println("------------------退出主线程----------------");
    }
}
