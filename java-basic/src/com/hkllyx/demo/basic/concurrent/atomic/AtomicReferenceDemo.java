package com.hkllyx.demo.basic.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 引用的原子性外包类
 * 同理还有：AtomicReferenceArray
 *
 * @author HKLLY
 * @date 2019-07-12
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User user = new User(20, "user1");
        AtomicReference<User> aUser = new AtomicReference<>(user);
        System.out.println(aUser);

        User newUser = new User(21, "user2");
        aUser.compareAndSet(newUser, newUser);
        System.out.println(aUser);

        aUser.compareAndSet(user, newUser);
        System.out.println(aUser);
    }
}
