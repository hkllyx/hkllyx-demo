package com.hkllyx.demo.basic.concurrent.others.simulation.distributingwork;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author HKLLY
 * @date 2019/4/14
 */
public class Reporter implements Runnable {
    private LinkedBlockingQueue<Car> carQueue;

    public Reporter(LinkedBlockingQueue<Car> cq) {
        carQueue = cq;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(carQueue.take());
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting Reporter via interrupt");
        }
        System.out.println("Reporter off");
    }
}
