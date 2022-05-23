package com.hkllyx.demo.basic.concurrent.others.simulation.distributingwork;

import java.util.concurrent.BrokenBarrierException;

/**
 * 机器人抽象模板
 *
 * @author HKLLY
 * @date 2019/4/14
 */
public abstract class AbstractRobot implements Runnable {
    /**
     * 所属组装间
     */
    protected CarAssembler assembler;
    /**
     * 所属机器人池
     */
    private RobotPool pool;
    /**
     * 是否参与工作
     */
    private boolean engage = false;

    public AbstractRobot(RobotPool p) {
        pool = p;
    }

    /**
     * perform service
     */
    protected abstract void performService();

    public AbstractRobot assignAssembler(CarAssembler assembler) {
        this.assembler = assembler;
        return this;
    }

    /**
     * 参与工作
     */
    public synchronized void engage() {
        engage = true;
        notifyAll();
    }

    /**
     * 能量不足，停止工作
     *
     * @throws InterruptedException ex
     */
    private synchronized void powerDown() throws InterruptedException {
        engage = false;
        assembler = null;
        pool.release(this);
        while (!engage) {
            wait();
        }
    }

    /**
     * 执行任务
     */
    @Override
    public void run() {
        try {
            powerDown();
            while (!Thread.interrupted()) {
                performService();
                assembler.barrier().await();
                powerDown();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting " + this + " via interrupt");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.println(this + " off");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
