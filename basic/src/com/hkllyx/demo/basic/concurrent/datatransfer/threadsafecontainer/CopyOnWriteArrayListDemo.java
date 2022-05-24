package com.hkllyx.demo.basic.concurrent.datatransfer.threadsafecontainer;

import com.hkllyx.demo.basic.concurrent.util.ExecutorUtils;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;

/**
 * @author HKLLY
 * @date 2019-08-23
 */
public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) throws InterruptedException {
        final CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        ExecutorService pool = ExecutorUtils.newFixedThreadPool(2);
        pool.execute(new IntProducer(list));
        pool.execute(new IntConsumer(list));

        Thread.sleep(1);
        pool.shutdownNow();
        System.out.println(list);
    }
}

class IntProducer implements Runnable {
    private final CopyOnWriteArrayList<Integer> list;

    public IntProducer(CopyOnWriteArrayList<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        int i = 0;
        while (!Thread.interrupted()) {
            list.add(i++);
        }
    }
}

class IntConsumer implements Runnable {
    private final CopyOnWriteArrayList<Integer> list;

    public IntConsumer(CopyOnWriteArrayList<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            int i = list.remove(0);
            System.out.println(i);
        }
    }
}
