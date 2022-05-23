package com.hkllyx.demo.basic.concurrent.threadlocal;

/**
 * @author HKLLY
 * @date 2019-08-22
 */
public class InheritableThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {
        final ThreadLocal<String> local = new InheritableThreadLocal<>();
        local.set("main");
        Thread thread = new Thread(() -> {
            System.out.println("A: " + local.get());
            local.set("A");
            System.out.println("A: " + local.get());
        });
        thread.start();
        thread.join();
        System.out.println(local.get());
    }
}
