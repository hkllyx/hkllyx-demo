package com.hkllyx.demo.basic.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author HKLLY
 * @date 2019-08-22
 */
public class AtomicIntegerArrayDemo {

    public static void main(String[] args) {
        AtomicIntegerArray aia = new AtomicIntegerArray(5);
        for (int i = 0; i < aia.length(); i++) {
            aia.set(i, i + 1);
        }
        System.out.println(aia);
    }
}
