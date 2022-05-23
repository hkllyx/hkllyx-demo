package com.hkllyx.demo.basic.generic.tuple;

/**
 * 包含4个对象的元组
 *
 * @author HKLLY
 * @date 2019/4/2
 */
public class FourTuple<A, B, C, D> extends ThreeTuple<A, B, C> {
    public final D d;

    public FourTuple(A a, B b, C c, D d) {
        super(a, b, c);
        this.d = d;
    }

    @Override
    public String toString() {
        return "(" + a + ", " + b + ", " + c + ", " + d + ")";
    }
}
