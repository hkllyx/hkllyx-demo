package com.hkllyx.demo.basic.concurrent.datatransfer.pipedio;

import com.hkllyx.demo.basic.concurrent.util.ExecutorUtils;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author HKLLY
 * @date 2019-07-11
 */
public class PipedIoDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 发送对象
        Sender sender = new Sender();
        // 接受对象
        Receiver receiver = new Receiver(sender);
        ExecutorService pool = ExecutorUtils.newFixedThreadPool(2);
        // 运行
        pool.execute(sender);
        pool.execute(receiver);
        TimeUnit.SECONDS.sleep(4);
        pool.shutdownNow();
    }
}
