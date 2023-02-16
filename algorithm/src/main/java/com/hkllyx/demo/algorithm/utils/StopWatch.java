package com.hkllyx.demo.algorithm.utils;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaoyong3
 * @date 2023/02/15
 */
public class StopWatch {
    private boolean started;
    private long timestamp;
    private long cost;

    public void start() {
        if (!started) {
            started = true;
            timestamp = System.currentTimeMillis();
        }
    }

    public void stop() {
        if (started) {
            started = false;
            cost += System.currentTimeMillis() - timestamp;
        }
    }

    public void resume() {
        cost = 0;
        timestamp = 0;
        started = false;
    }

    @Override
    public String toString() {
        return "StopWatch{cost=" + cost + "ms}";
    }

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        TimeUnit.MILLISECONDS.sleep(100);
        stopWatch.start();
        TimeUnit.MILLISECONDS.sleep(200);
        stopWatch.stop();

        TimeUnit.MILLISECONDS.sleep(300);

        stopWatch.start();
        TimeUnit.MILLISECONDS.sleep(400);
        stopWatch.stop();
        TimeUnit.MILLISECONDS.sleep(500);
        stopWatch.stop();

        System.out.println(stopWatch);
    }
}
