package com.hkllyx.demo.basic.generic.tuple;

/**
 * 包含5个对象的元组
 *
 * @author HKLLY
 * @date 2019/4/2
 */
public class FiveTuple<A, B, C, D, E> extends FourTuple<A, B, C, D> {
    public final E e;

    public FiveTuple(A a, B b, C c, D d, E e) {
        super(a, b, c, d);
        this.e = e;
    }

    @Override
    public String toString() {
        return "(" + a + ", " + b + ", " + c + ", " + d +
                ", " + e + ")";
    }
}
