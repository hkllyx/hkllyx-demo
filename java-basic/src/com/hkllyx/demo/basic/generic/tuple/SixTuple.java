package com.hkllyx.demo.basic.generic.tuple;

/**
 * 包含6个对象的元组
 *
 * @author HKLLY
 * @date 2019/4/2
 */
public class SixTuple<A, B, C, D, E, F> extends FiveTuple<A, B, C, D, E> {
    public final F f;

    public SixTuple(A a, B b, C c, D d, E e, F f) {
        super(a, b, c, d, e);
        this.f = f;
    }

    @Override
    public String toString() {
        return "(" + a + ", " + b + ", " + c + ", " + d +
                ", " + e + ", " + f + ")";
    }
}
