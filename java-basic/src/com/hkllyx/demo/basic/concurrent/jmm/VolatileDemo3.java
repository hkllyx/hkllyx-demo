package com.hkllyx.demo.basic.concurrent.jmm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author HKLLY
 * @date 2019-08-18
 */
public class VolatileDemo3 {
    static volatile int x, y;

    public static void main(String[] args) throws InterruptedException {
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>(2);
        for (int i = 0; i < 10000000; i++) {
            x = 0;
            y = 0;
            map.clear();
            Thread t1 = new Thread(() -> {
                int a = y;//  1
                x = 1;//  2
                map.put("a", a);
            });
            Thread t2 = new Thread(() -> {
                int b = x;//  3
                x = 1;//  4
                map.put("b", b);
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            /*
              不发生重排序：
                        1 -> 2 -> 3 -> 4: a = 0, b = 1
                        3 -> 4 -> 1 -> 2: a = 1, b = 0
                        1 -> 3 -> 4 -> 2: a = 0, b = 0
                        3 -> 1 -> 2 -> 4: a = 0, b = 0
              1，2 重排序：
                        2 -> 1 -> 3 -> 4: a = 0, b = 1
                        3 -> 4 -> 2 -> 1: a = 1, b = 0
                        3 -> 2 -> 1 -> 4: a = 1, b = 0
                        2 -> 3 -> 4 -> 1: a = 1, b = 1
              3, 4 重排序：同理
             */

            String s = "[a = " + map.get("a") + ", b = " + map.get("b") + "]";
            set.add(s);
            System.out.println(set);
            // Thread.yield();
        }
    }
}
