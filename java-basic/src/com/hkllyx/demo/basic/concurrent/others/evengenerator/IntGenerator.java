package com.hkllyx.demo.basic.concurrent.others.evengenerator;

/**
 * @author HKLLY
 * @date 2019/4/11
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false;

    /**
     * generate a int.
     *
     * @return int
     */
    public abstract int next();

    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
