package com.hkllyx.demo.basic.concurrent.others.evengenerator;

/**
 * 使用volatile进行可见性控制，不能保证原子性
 *
 * @author HKLLY
 * @date 2019-07-10
 */
public class VolatileEvenGenerator extends IntGenerator {
    private volatile int currentEvenValue = 0;

    public static void main(String[] args) {
        EvenChecker.test(new VolatileEvenGenerator(), 10);
    }

    @Override
    public int next() {
        currentEvenValue++;
        // Cause failure faster.
        // Thread.yield();
        currentEvenValue++;
        return currentEvenValue;
    }
}
