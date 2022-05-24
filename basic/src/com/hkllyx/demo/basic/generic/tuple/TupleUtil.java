package com.hkllyx.demo.basic.generic.tuple;

/**
 * 生成包含多个对象的元组
 *
 * @author HKLLY
 * @date 2019/4/2
 */
public class TupleUtil {

    private TupleUtil() {
    }

    /**
     * 生成包含2个对象的元组
     *
     * @param a   对象a
     * @param b   对象b
     * @param <A> 对象a的类型
     * @param <B> 对象b的类型
     * @return 包含2个对象的元组
     */
    public static <A, B> TwoTuple<A, B> generateTuple(A a, B b) {
        return new TwoTuple<>(a, b);
    }

    /**
     * 生成包含3个对象的元组
     *
     * @param a   对象a
     * @param b   对象b
     * @param c   对象c
     * @param <A> 对象a的类型
     * @param <B> 对象b的类型
     * @param <C> 对象c的类型
     * @return 包含3个对象的元组
     */
    public static <A, B, C> ThreeTuple<A, B, C> generateTuple(A a, B b, C c) {
        return new ThreeTuple<>(a, b, c);
    }

    /**
     * 生成包含4个对象的元组
     *
     * @param a   对象a
     * @param b   对象b
     * @param c   对象c
     * @param d   对象d
     * @param <A> 对象a的类型
     * @param <B> 对象b的类型
     * @param <C> 对象c的类型
     * @param <D> 对象d的类型
     * @return 包含4个对象的元组
     */
    public static <A, B, C, D> FourTuple<A, B, C, D> generateTuple(A a, B b, C c, D d) {
        return new FourTuple<>(a, b, c, d);
    }

    /**
     * 生成包含5个对象的元组
     *
     * @param a   对象a
     * @param b   对象b
     * @param c   对象c
     * @param d   对象d
     * @param e   对象e
     * @param <A> 对象a的类型
     * @param <B> 对象b的类型
     * @param <C> 对象c的类型
     * @param <D> 对象d的类型
     * @param <E> 对象e的类型
     * @return 包含5个对象的元组
     */
    public static <A, B, C, D, E> FiveTuple<A, B, C, D, E> generateTuple
    (A a, B b, C c, D d, E e) {
        return new FiveTuple<>(a, b, c, d, e);
    }

    /**
     * 生成包含6个对象的元组
     *
     * @param a   对象a
     * @param b   对象b
     * @param c   对象c
     * @param d   对象d
     * @param e   对象e
     * @param f   对象f
     * @param <A> 对象a的类型
     * @param <B> 对象b的类型
     * @param <C> 对象c的类型
     * @param <D> 对象d的类型
     * @param <E> 对象e的类型
     * @param <F> 对象f的类型
     * @return 包含6个对象的元组
     */
    public static <A, B, C, D, E, F> SixTuple<A, B, C, D, E, F> generateTuple
    (A a, B b, C c, D d, E e, F f) {
        return new SixTuple<>(a, b, c, d, e, f);
    }
}
