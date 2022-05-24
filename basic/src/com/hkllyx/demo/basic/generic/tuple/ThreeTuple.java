package com.hkllyx.demo.basic.generic.tuple;

/**
 * 包含3个对象的元组
 *
 * @author HKLLY
 * @date 2019/4/2
 */
public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
    public final C c;

    public ThreeTuple(A a, B b, C c) {
        super(a, b);
        this.c = c;
    }

    @Override
    public String toString() {
        return "(" + a + ", " + b + ", " + c + ")";
    }
}
