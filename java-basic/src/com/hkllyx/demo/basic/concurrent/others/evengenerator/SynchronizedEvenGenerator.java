package com.hkllyx.demo.basic.concurrent.others.evengenerator;

/**
 * 使用synchronized关键字获取对象锁（监视器）实现执行控制
 *
 * @author HKLLY
 * @date 2019/4/11
 */
public class SynchronizedEvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedEvenGenerator(), 10);
    }

    @Override
    public synchronized int next() {
        currentEvenValue++;
        // Cause failure faster.
        Thread.yield();
        currentEvenValue++;
        return currentEvenValue;
    }
}
