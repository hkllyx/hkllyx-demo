package com.hkllyx.demo.basic.concurrent.atomic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author HKLLY
 * @date 2019-08-22
 */
public class UnSafeDemo {

    public static void main(String[] args) throws Exception {
        // Unsafe unsafe = Unsafe.getUnsafe(); //  不可直接调用
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        System.out.println(unsafe);

        //  内存操作
        long memoAddr = unsafe.allocateMemory(1);
        unsafe.putAddress(memoAddr, Long.MAX_VALUE);
        long data = unsafe.getAddress(memoAddr);
        System.out.println(Long.MAX_VALUE + " : " + data);

        //  实例化操作，实例化时没有调用构造方法。
        // int i = (int) unsafe.allocateInstance(int.class); //  不能创建基本类型数据
        User user = (User) unsafe.allocateInstance(User.class);
        System.out.println(user);

        //  对象操作
        Field id = User.class.getDeclaredField("id");
        Field age = User.class.getDeclaredField("age");
        Field name = User.class.getDeclaredField("name");
        //  获取摸个字段的偏移，并设置该字段的值
        unsafe.putIntVolatile(user, unsafe.objectFieldOffset(id), 1);
        unsafe.putIntVolatile(user, unsafe.objectFieldOffset(age), 22);
        unsafe.putObjectVolatile(user, unsafe.objectFieldOffset(name), "hkllyx");
        System.out.println(user);
        Class<?> clazz = (Class<?>) unsafe.staticFieldBase(User.class.getDeclaredField("counter"));
        System.out.println(clazz);

        //  数组操作
        int[] arr = new int[1000];
        //  获取数组第一个元素的偏移
        int off = unsafe.arrayBaseOffset(arr.getClass());
        //  获取数组元素的大小，单位为字节
        int size = unsafe.arrayIndexScale(arr.getClass());
        System.out.println(off + " : " + size);

        //  CAS 操作
        unsafe.compareAndSwapInt(user, unsafe.objectFieldOffset(id), 2, 100);
        System.out.println("CAS 失败：" + user);
        unsafe.compareAndSwapInt(user, unsafe.objectFieldOffset(id), 1, 100);
        System.out.println("CAS 成功：" + user);

        System.out.println(31 - Integer.numberOfLeadingZeros(4));
    }
}

