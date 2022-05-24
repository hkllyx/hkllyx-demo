package com.hkllyx.demo.basic.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;

/**
 * 1）面向流的侦听套接字的可选通道。<br>
 * 2）通过调用该类的open()方法创建服务器套接字通道。不能为任意预先存在的ServerSocket创建通道。<br>
 * 3）新创建的服务器套接字通道已打开，但尚未绑定。尝试调用未绑定的服务器套接字通道的accept()方法将引发NotYetBoundException。<br>
 * 4）可以通过调用该类定义的绑定（bind）方法之一来绑定服务器套接字通道。<br>
 *
 * @author HKLLY
 * @date 2019-07-19
 */
public class ServerSocketChannelDemo {
    
    public static void main(String[] args) {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress("localhost", 8080));
            
            System.out.println("ssc.isOpen() = " + ssc.isOpen());
            
            System.out.println("ssc.getLocalAddress() = " + ssc.getLocalAddress());
            System.out.println("ssc.socket() = " + ssc.socket());
            System.out.println("ssc.accept() = " + ssc.accept());
            
            System.out.println("ssc.isBlocking() = " + ssc.isBlocking());
            System.out.println("ssc.isRegistered() = " + ssc.isRegistered());
            SelectionKey key = ssc.register(null, SelectionKey.OP_ACCEPT);
            key = ssc.register(null, SelectionKey.OP_READ, null);
            key = ssc.keyFor(null);
            int ops = ssc.validOps();
            Object lock = ssc.blockingLock();
            SelectorProvider provider = ssc.provider();
            
            ssc.getOption(null);
            ssc.supportedOptions();
            ssc.setOption(null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
