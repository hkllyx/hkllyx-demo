package com.hkllyx.demo.basic.concurrent.others.simulation.distributingwork;

/**
 * @author HKLLY
 * @date 2019/4/14
 */
public class Car {
    private final int id;
    /**
     * 引擎
     */
    private boolean engine = false;
    /**
     * 驱动链
     */
    private boolean driveTrain = false;
    /**
     * 车轮
     */
    private boolean wheels = false;

    public Car(int id) {
        this.id = id;
    }

    public synchronized int getId() {
        return id;
    }

    /**
     * 添加引擎
     */
    public synchronized void addEngine() {
        engine = true;
    }

    /**
     * 添加驱动链
     */
    public synchronized void addDriveTrain() {
        driveTrain = true;
    }

    /**
     * 添加车轮
     */
    public synchronized void addWheels() {
        wheels = true;
    }

    @Override
    public synchronized String toString() {
        return "Car" + id + "[engine: " + engine + ", driveTrain: " +
                driveTrain + ", wheels: " + wheels + "]";
    }
}
