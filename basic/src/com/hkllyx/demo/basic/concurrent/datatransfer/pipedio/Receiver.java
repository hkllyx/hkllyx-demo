package com.hkllyx.demo.basic.concurrent.datatransfer.pipedio;

import java.io.IOException;
import java.io.PipedReader;

/**
 * @author HKLLY
 * @date 2019/4/12
 */
public class Receiver implements Runnable {
    private final PipedReader in;

    public Receiver(Sender sender) throws IOException {
        in = new PipedReader(sender.getPipedWriter());
    }

    @Override
    public void run() {
        try {
            System.out.print("Read: ");
            while (true) {
                // 接受char
                System.out.print((char) in.read() + ", ");
            }
        } catch (IOException e) {
            System.out.println("\nReceiver IOException");
        }
    }
}
