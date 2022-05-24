package com.hkllyx.demo.basic.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 对象的属性修改类型原子类<br>
 * 同理还有：AtomicLongFieldUpdater、AtomicReferenceFieldUpdater
 *
 * @author HKLLY
 * @date 2019-07-12
 */
public class AtomicIntegerFieldUpdaterDemo {

    public static void main(String[] args) {
        // 更新的域必须为当前类可见，且为volatile修饰
        AtomicIntegerFieldUpdater<User> updater =
                AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
        User user = new User(20, "user1");
        System.out.println(user);

        System.out.println(updater.compareAndSet(user, 21, 21));
        System.out.println(user);

        System.out.println(updater.compareAndSet(user, 20, 21));
        System.out.println(user);
    }
}
