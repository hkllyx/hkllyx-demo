package com.hkllyx.demo.basic.io.net.tcp.client;

import com.hkllyx.demo.basic.io.net.ClientUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author HKLLY
 * @date 2019-07-19
 */
public class NioEchoClient implements Runnable {
    private final String info;
    private final String host;
    private final int port;

    public NioEchoClient(String info, String host, int port) {
        this.info = info;
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        ClientUtils.connect(NioEchoClient.class, 10, "localhost", 9999);
    }

    @Override
    public void run() {
        init();
    }

    public void init() {
        //初始化客户端通道，并连接到远程服务器
        try (SocketChannel client = SocketChannel.open()) {
            client.connect(new InetSocketAddress(host, port));
            //向服务器发送消息
            ByteBuffer sndBuff = ByteBuffer.wrap(info.getBytes());
            client.write(sndBuff);
            //关闭输出流
            client.shutdownOutput();

            //接受服务端反馈
            ByteBuffer rcvBuff = ByteBuffer.allocate(1024);
            StringBuilder feed = new StringBuilder();
            while (client.read(rcvBuff) != -1) {
                rcvBuff.flip();
                while (rcvBuff.hasRemaining()) {
                    feed.append((char) rcvBuff.get());
                }
                rcvBuff.clear();
            }

            System.out.println(feed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
