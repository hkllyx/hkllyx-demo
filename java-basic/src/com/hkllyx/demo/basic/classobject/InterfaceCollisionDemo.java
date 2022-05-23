package com.hkllyx.demo.basic.classobject;

/**
 * @author HKLLY
 * @date 2019-08-12
 */
public class InterfaceCollisionDemo {
    public static void main(String[] args) {
        Foo1 collision = new Collision() {
        };
        collision.doSomething();
    }
}

interface Foo1 {
    default void doSomething() {
        System.out.println("Foo1");
    }
}

interface Foo2 {
    default void doSomething() {
        System.out.println("Foo2");
    }
}

interface Collision extends Foo1, Foo2 {
    @Override
    default void doSomething() {
        System.out.println("Collision");
    }
}