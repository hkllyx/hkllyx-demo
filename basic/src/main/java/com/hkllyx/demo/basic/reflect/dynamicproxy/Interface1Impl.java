package com.hkllyx.demo.basic.reflect.dynamicproxy;

/**
 * @author HKLLY
 * @date 2019-07-08
 */
public class Interface1Impl implements Interface1 {

    @Override
    public void do1(String thing) {
        System.out.println("do1 " + thing);
    }
}
