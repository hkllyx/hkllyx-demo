package com.hkllyx.demo.basic.concurrent.others.evengenerator;

/**
 * 偶数整数生成器
 *
 * @author HKLLY
 * @date 2019/4/11
 */
public class EvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator(), 10);
    }

    @Override
    public int next() {
        // 非原子性操作
        // Danger point: while a thread do this,
        // another thread may be executed
        currentEvenValue++;
        // Force execute another thread.
        Thread.yield();
        currentEvenValue++;
        return currentEvenValue;
    }
}
