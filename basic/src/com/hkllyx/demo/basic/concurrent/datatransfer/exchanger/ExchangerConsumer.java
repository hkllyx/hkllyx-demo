package com.hkllyx.demo.basic.concurrent.datatransfer.exchanger;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @author HKLLY
 * @date 2019/4/14
 */
public class ExchangerConsumer<T> implements Runnable {
    private List<T> holder;
    private volatile T value;
    private final Exchanger<List<T>> exchanger;

    public ExchangerConsumer(Exchanger<List<T>> exchanger, List<T> holder) {
        this.exchanger = exchanger;
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 交换（同步）holder
                holder = exchanger.exchange(holder);
                // 递归holder，移除一个T对象，并将最后一个对象保存在value
                Iterator<T> it = holder.iterator();
                while (it.hasNext()) {
                    value = it.next();
                    holder.remove(value);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer exchanger Interrupted");
        }
        System.out.println("Final value: " + value);
    }
}
