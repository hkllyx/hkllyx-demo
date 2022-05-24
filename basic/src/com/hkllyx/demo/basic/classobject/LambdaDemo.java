package com.hkllyx.demo.basic.classobject;

import javax.swing.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author HKLLY
 * @date 2019-08-12
 */
public class LambdaDemo {

    public static void main(String[] args) throws InterruptedException {
        // 使用 lambda 表达式重写回调
        Timer t = new Timer(1000, e -> System.out.println("当前时间是：" + new Date()));
        t.start();

        TimeUnit.SECONDS.sleep(5);
    }
}
