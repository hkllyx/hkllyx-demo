package com.hkllyx.demo.basic.io.net.udp.client;

import com.hkllyx.demo.basic.io.net.ClientUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @author HKLLY
 * @date 2019-07-20
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
        // 初始话发送、接受channel
        try (DatagramChannel channel = DatagramChannel.open()) {
            //向服务器发送信息
            channel.send(ByteBuffer.wrap(info.getBytes()), new InetSocketAddress(host, port));

            //接收客户端信息
            ByteBuffer recBuff = ByteBuffer.allocate(1024);
            channel.receive(recBuff);
            recBuff.flip();
            StringBuilder feed = new StringBuilder();
            while (recBuff.hasRemaining()) {
                feed.append((char)recBuff.get());
            }

            System.out.println(feed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
