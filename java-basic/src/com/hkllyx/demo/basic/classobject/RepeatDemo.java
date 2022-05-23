package com.hkllyx.demo.basic.classobject;

import javax.swing.*;
import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;

/**
 * @author HKLLY
 * @date 2019-08-12
 */
public class RepeatDemo {
    public static void main(String[] args) throws InterruptedException {
        repeat(1000, "closure");

        Runnable action = () -> System.out.println("do something");
        repeat(5, action);

        IntConsumer consumer = i -> System.out.println("do something at : " + i);
        repeat(5, consumer);
    }

    static void repeat(int delay, String text) {
        Timer t = new Timer(delay, e -> {
            //text = "change"; // 编译错误：text 是 final 的
            System.out.println(text);
        });
        t.start();
        try {
            // 让 repeat 方法所在的线程（在 main 方法中调用为主线程）睡眠 5 秒以便 Timer 线程执行
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void repeat(int count, Runnable action) {
        for (int i = 0; i < count; i++) {
            // 不使用线程，只是使用其 run 方法
            action.run();
        }
    }

    static void repeat(int count, IntConsumer consumer) {
        for (int i = 0; i < count; i++) {
            // IntConsumer 每次接受一个 int，即消费一个 int
            consumer.accept(i);
        }
    }
}
