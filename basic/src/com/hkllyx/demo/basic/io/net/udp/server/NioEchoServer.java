package com.hkllyx.demo.basic.io.net.udp.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

/**
 * @author HKLLY
 * @date 2019-07-20
 */
public class NioEchoServer {
    private final int port;

    public NioEchoServer(int port) throws IOException {
        this.port = port;
        init();
    }

    public static void main(String[] args) throws IOException {
        new NioEchoServer(9999);
    }

    public void init() throws IOException {
        try (Selector selector = Selector.open();
                DatagramChannel channel = DatagramChannel.open()) {
            channel.bind(new InetSocketAddress(port));
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);

            System.out.println("Waiting for Clients...");
            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    if (key.isReadable()) {
                        //接收客户端信息
                        ByteBuffer revBuff = ByteBuffer.allocate(1024);
                        DatagramChannel channel1 = (DatagramChannel) key.channel();
                        SocketAddress clientAddr = channel1.receive(revBuff);
                        revBuff.flip();
                        StringBuilder info = new StringBuilder();
                        while (revBuff.hasRemaining()) {
                            info.append((char) revBuff.get());
                        }
                        String infoStr = info.toString();
                        System.out.println(infoStr);

                        //发送消息给客户端
                        channel.send(ByteBuffer.wrap(infoStr.getBytes()), clientAddr);
                    }
                    it.remove();
                }
            }
        }
    }
}
