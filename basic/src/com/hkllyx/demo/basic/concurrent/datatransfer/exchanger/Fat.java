package com.hkllyx.demo.basic.concurrent.datatransfer.exchanger;

/**
 * 初始化花销大的类
 *
 * @author HKLLY
 * @date 2019/4/14
 */
public class Fat {
    private static int counter = 0;
    private final int id = counter++;
    private volatile double d;

    public Fat() {
        //  花销大的初始化过程
        for (int i = 1; i < 10000; i++) {
            d += (Math.PI + Math.E) / i;
        }
    }

    @Override
    public String toString() {
        return "Fat " + id;
    }
}
