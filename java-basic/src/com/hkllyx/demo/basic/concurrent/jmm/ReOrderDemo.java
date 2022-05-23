package com.hkllyx.demo.basic.concurrent.jmm;

import java.util.HashMap;
import java.util.Map;

/**
 * 重排序造成的可见性问题
 *
 * @author HKLLY
 * @date 2019-08-18
 */
public class ReOrderDemo {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            ReOrder demo = new ReOrder();
            new Thread(demo::writer).start();
            new Thread(demo::reader).start();
        }
        //  结果全为 1，原因还未谈清楚！！！
        System.out.println(ReOrder.RESULT);
    }
}

class ReOrder {
    private int a = 0;
    private boolean flag = false;
    public static final Map<Integer, Integer> RESULT = new HashMap<>(2);

    public void writer() {
        a = 1;//  1
        flag = true;//  2
    }

    public void reader() {
        if (flag) {
            //  如果 1 和 2 发生了重排序，flag 为 true 时 a 的值就应该为 0，否则为 1.
            if (RESULT.containsKey(a)) {
                RESULT.put(a, RESULT.get(a) + 1);
            } else {
                RESULT.put(a, 1);
            }
        }
    }
}
