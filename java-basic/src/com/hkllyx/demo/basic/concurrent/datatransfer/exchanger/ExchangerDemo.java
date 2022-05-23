package com.hkllyx.demo.basic.concurrent.datatransfer.exchanger;

import com.hkllyx.demo.basic.generic.generator.BasicGenerator;
import com.hkllyx.demo.basic.generic.generator.Generator;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author HKLLY
 * @date 2019/4/14
 */
public class ExchangerDemo {
    static int size = 10;

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        // 创建交换List<Fat>的Exchanger
        Exchanger<List<Fat>> exchanger = new Exchanger<>();
        // 生产者列表，消费者列表
        List<Fat> producerList = new CopyOnWriteArrayList<>();
        List<Fat> consumerList = new CopyOnWriteArrayList<>();
        // Fat生成器
        Generator<Fat> fatGenerator = new BasicGenerator<>(Fat.class);

        // 执行生产者消费者线程
        pool.execute(new ExchangerProducer<>(fatGenerator, exchanger, producerList));
        pool.execute(new ExchangerConsumer<>(exchanger, consumerList));
        // 等待3秒后关闭线程池
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pool.shutdownNow();
        }
    }
}
