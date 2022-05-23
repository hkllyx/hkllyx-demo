package com.hkllyx.demo.basic.concurrent.others.simulation.carmaintaining;

/**
 * @author HKLLY
 * @date 2019/4/12
 */
public class Car {
    /**
     * 是否已经打蜡
     */
    private boolean waxOn = false;

    /**
     * 打蜡
     */
    public synchronized void waxed() {
        // 设为已打蜡
        waxOn = true;
        // 唤醒其他线程（抛光）
        notifyAll();
    }

    /**
     * 抛光
     */
    public synchronized void buffed() {
        // 抛光后就设为没有打蜡
        waxOn = false;
        // 唤醒其他线程（打蜡）
        notifyAll();
    }

    /**
     * 等待打蜡
     *
     * @throws InterruptedException ex
     */
    public synchronized void waitingForWaxing()
            throws InterruptedException {
        // 没有打蜡，等待，释放锁
        if (!waxOn) {
            wait();
        }
    }

    /**
     * 等待抛光
     *
     * @throws InterruptedException ex
     */
    public synchronized void waitingForBuffing()
            throws InterruptedException {
        // 已打蜡，等待，释放锁
        if (waxOn) {
            wait();
        }
    }
}
