package com.hkllyx.demo.basic.concurrent.datatransfer.exchanger;

import com.hkllyx.demo.basic.generic.generator.Generator;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @author HKLLY
 * @date 2019/4/14
 */
public class ExchangerProducer<T> implements Runnable {
    private final Generator<T> generator;
    private final Exchanger<List<T>> exchanger;
    private List<T> holder;

    public ExchangerProducer(Generator<T> generator, Exchanger<List<T>> exchanger, List<T> holder) {
        this.generator = generator;
        this.exchanger = exchanger;
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                for (int i = 0; i < ExchangerDemo.size; i++) {
                    // 生成T对象，放入holder中
                    holder.add(generator.next());
                    // 交换（同步）holder
                    holder = exchanger.exchange(holder);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Producer exchange interrupted");
        }
    }
}
