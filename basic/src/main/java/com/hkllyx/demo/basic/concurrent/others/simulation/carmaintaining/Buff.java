package com.hkllyx.demo.basic.concurrent.others.simulation.carmaintaining;

import java.util.concurrent.TimeUnit;

/**
 * 抛光任务，抛光前需要打蜡
 *
 * @author HKLLY
 * @date 2019/4/12
 */
public class Buff implements Runnable {
    private Car car;

    public Buff(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 若没有打蜡，先释放锁，等待打蜡
                car.waitingForWaxing();
                // 模拟抛光
                System.out.println("Start Buffing");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("Exit Buff via interrupt");
        }
        System.out.println("End Buff via task");
    }
}