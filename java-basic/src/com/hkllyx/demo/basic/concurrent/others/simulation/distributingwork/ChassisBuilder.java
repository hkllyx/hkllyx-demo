package com.hkllyx.demo.basic.concurrent.others.simulation.distributingwork;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 生成底盘
 *
 * @author HKLLY
 * @date 2019/4/14
 */
public class ChassisBuilder implements Runnable {
    private LinkedBlockingQueue<Car> carQueue;
    private int counter = 0;

    public ChassisBuilder(LinkedBlockingQueue<Car> carQueue) {
        this.carQueue = carQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                Car c = new Car(counter++);
                System.out.println("ChassisBuilder created " + c);
                carQueue.put(c);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted: ChassisBuilder");
        }
        System.out.println("ChassisBuilder off");
    }
}
