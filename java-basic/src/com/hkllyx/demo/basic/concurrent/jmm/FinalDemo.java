package com.hkllyx.demo.basic.concurrent.jmm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author HKLLY
 * @date 2019-08-20
 */
public class FinalDemo {
    int i;
    final int j;

    static FinalDemo demo;

    public FinalDemo() {
        i = 1;
        j = 1;
    }

    public static void writer() {
        //  能保证 final 字段（j）初始化后立即对其他线程可见，
        //  但不能保证非 final 字段（i）对其他线程可见
        demo = new FinalDemo();
    }

    public static String reader() {
        FinalDemo obj = demo;
        return obj == null ? null : "i = " + obj.i + ", j = " + demo.j;
    }

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 100000000; i++) {
            new Thread(FinalDemo::writer).start();
            new Thread(() -> {
                String s = reader();
                set.add(s);
            }).start();
            System.out.println(set);
        }
    }
}
