package com.hkllyx.demo.basic.generic.tuple;

/**
 * @author HKLLY
 * @date 2019-07-18
 */
public class TupleDemo {

    public static void main(String[] args) {
        System.out.println(TupleUtil.generateTuple("a", "b"));
        System.out.println(TupleUtil.generateTuple("a", "b", "c"));
        System.out.println(TupleUtil.generateTuple("a", "b", "c", "d"));
        System.out.println(TupleUtil.generateTuple("a", "b", "c", "d", "e"));
        System.out.println(TupleUtil.generateTuple("a", "b", "c", "d", "e", "f"));
    }
}
