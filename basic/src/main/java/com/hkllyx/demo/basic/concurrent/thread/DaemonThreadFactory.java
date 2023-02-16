package com.hkllyx.demo.basic.concurrent.thread;

import java.util.concurrent.ThreadFactory;

/**
 * @author HKLLY
 * @date 2019-07-11
 */
public class DaemonThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable task) {
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        return thread;
    }
}
