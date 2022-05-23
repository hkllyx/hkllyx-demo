package com.hkllyx.demo.basic.jvm;

/**
 * @author HKLLY
 * @date 2019-08-13
 */
public class GcDemo {
    private static final int _MB = 1024 * 1024;
    public static void main(String[] args) {
        // -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
        test();
    }
    
    private static void test() {
        byte[] a1, a2, a3, a4;
        a1= new byte[2* _MB];
        a2= new byte[2* _MB];
        a3= new byte[2* _MB];
        a4= new byte[4* _MB];
    }
}
