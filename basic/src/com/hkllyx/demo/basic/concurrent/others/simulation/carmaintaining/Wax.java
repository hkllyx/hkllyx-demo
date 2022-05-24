package com.hkllyx.demo.basic.concurrent.others.simulation.carmaintaining;

import java.util.concurrent.TimeUnit;

/**
 * 打蜡任务，打蜡后抛光
 *
 * @author HKLLY
 * @date 2019/4/12
 */
public class Wax implements Runnable {
    private Car car;

    public Wax(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 模拟打蜡
                System.out.println("Start Waxing");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                // 释放锁，等待抛光
                car.waitingForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.println("Exit Wax via interrupt");
        }
        System.out.println("Exit Wax via task");
    }
}
