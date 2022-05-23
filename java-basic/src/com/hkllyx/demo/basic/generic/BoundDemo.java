package com.hkllyx.demo.basic.generic;

import java.util.LinkedList;
import java.util.List;

/**
 * @author HKLLY
 * @date 2019-07-08
 */
public class BoundDemo {

    static void testUpperBound() {
        //Plate<Fruit> p = new Plate<Apple>(new Apple());
        Plate<? extends Fruit> p = new Plate<Apple>(new Apple());
        //p = new Plate<Apple>(new Fruit());
        //p.set(new Object());
        //p.set(new Fruit());
        //p.set(new Apple());
        //p.set(new RedApple());
        p.set(null);

        //Apple a = p.get();
        Fruit f = p.get();
    }

    static void testLowerBound() {
        Plate<? super RedApple> p = new Plate<Apple>(new Apple());
        //p = new Plate<Apple>(new Fruit());
        //p.set(new Object());
        //p.set(new Fruit());
        //p.set(new Apple());
        p.set(new RedApple());
        p.set(null);

        //Apple a = p.get();
        //Fruit f = p.get();
        Object o = p.get();
    }

    static void testNonBound() {
        List<?> list = new LinkedList<>();
        //list.add(new Object());
        list.add(null);

        Object o = list.get(0);
    }
}

class Fruit {
}

class Apple extends Fruit {
}

class RedApple extends Apple {
}

class Plate<T> {
    private T item;

    public Plate(T item) {
        this.item = item;
    }

    public void set(T t) {
        item = t;
    }

    public T get() {
        return item;
    }
}
