package com.hkllyx.demo.basic.io.net.udp.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 模拟UDP传输服务器端
 *
 * @author HKLLY
 * @date 2019-07-18
 */
public class EchoServer {
    private final int port;
    
    public EchoServer(int port) throws IOException {
        this.port = port;
        init();
    }
    
    public static void main(String[] args) throws IOException {
        new EchoServer(9999);
    }
    
    public void init() throws IOException {
        try (DatagramSocket server = new DatagramSocket(port)) {
            System.out.println("Waiting for clients...");
            while (true) {
                //接收数据
                byte[] info = new byte[1024];
                DatagramPacket revPk = new DatagramPacket(info, info.length);
                server.receive(revPk);
                String infoStr = new String(info, 0, revPk.getLength());
                System.out.println(infoStr);

                byte[] feed = infoStr.getBytes();
                DatagramPacket sendPk = new DatagramPacket(feed, feed.length, revPk.getSocketAddress());
                //反馈数据
                server.send(sendPk);
            }
        }
    }
}
