package com.hkllyx.demo.basic.concurrent.others.simulation.distributingwork;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 组装间
 *
 * @author HKLLY
 * @date 2019/4/14
 */
public class CarAssembler implements Runnable {
    /**
     * 底盘（初始）列队，完成列队
     */
    private LinkedBlockingQueue<Car> chassisQueue, finishingQueue;
    /**
     * 机器人池
     */
    private RobotPool robotPool;
    /**
     * 要组装的车
     */
    private Car car;
    private CyclicBarrier barrier = new CyclicBarrier(4);

    public CarAssembler(LinkedBlockingQueue<Car> cq,
            LinkedBlockingQueue<Car> fq, RobotPool rp) {
        chassisQueue = cq;
        finishingQueue = fq;
        robotPool = rp;
    }

    public Car car() {
        return car;
    }

    public CyclicBarrier barrier() {
        return barrier;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 从底盘列队取出底盘
                car = chassisQueue.take();
                // 雇佣机器人工作
                robotPool.hire(EngineRobot.class, this);
                robotPool.hire(DriveTrainRobot.class, this);
                robotPool.hire(WheelRobot.class, this);
                // 全部机器人完成才能组装
                barrier.await();
                // 组装完烦人完成列队
                finishingQueue.put(car);
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting CarAssembler via interrupt");
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("CarAssembler off");
    }
}
