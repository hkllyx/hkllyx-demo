package com.hkllyx.demo.basic.concurrent.atomic;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author HKLLY
 * @date 2019-08-22
 */
public class AtomicMarkableReferenceDemo {

    public static void main(String[] args) {
        User user = new User(22, "hkllyx");
        AtomicMarkableReference<User> amr = new AtomicMarkableReference<>(user, true);
        System.out.println("ref = " + amr.getReference() + ", mark = " + amr.isMarked());

        amr.attemptMark(user, false);
        System.out.println("ref = " + amr.getReference() + ", mark = " + amr.isMarked());

        User newUser = new User(22, "HKLLYX");
        //  mark 不匹配
        amr.compareAndSet(user, newUser, true, false);
        System.out.println("ref = " + amr.getReference() + ", mark = " + amr.isMarked());
        //  mark 匹配
        amr.compareAndSet(user, newUser, false, true);
        System.out.println("ref = " + amr.getReference() + ", mark = " + amr.isMarked());
    }
}
