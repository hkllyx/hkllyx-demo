package com.hkllyx.demo.basic.concurrent.datatransfer.pipedio;

import java.io.IOException;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author HKLLY
 * @date 2019/4/12
 */
public class Sender implements Runnable {
    private final Random rand = new Random(47);
    private final PipedWriter out = new PipedWriter();

    /**
     * 获取输出流
     */
    public PipedWriter getPipedWriter() {
        return out;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                for (char c = 'A'; c <= 'z'; c++) {
                    // 输出char
                    out.write(c);
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
