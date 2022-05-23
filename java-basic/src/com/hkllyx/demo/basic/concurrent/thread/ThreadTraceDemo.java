package com.hkllyx.demo.basic.concurrent.thread;

import java.util.Map;

/**
 * @author HKLLY
 * @date 2019-08-21
 */
public class ThreadTraceDemo {

    public static void main(String[] args) {
        System.out.println("Thread.activeCount() = " + Thread.activeCount());
        System.out.println();
        Map<Thread, StackTraceElement[]> traces = Thread.getAllStackTraces();
        for (Thread t : traces.keySet()) {
            System.out.println(t.getName() + ":" + t.isDaemon());
            for (StackTraceElement e : traces.get(t)) {
                System.out.println("    " + e.getLineNumber() + ":" + e.getClassName() + "." + e.getMethodName());
            }
        }
    }
}
