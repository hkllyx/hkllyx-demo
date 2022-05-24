package com.hkllyx.demo.basic.io.net.udp.client;

import com.hkllyx.demo.basic.io.net.ClientUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 模拟UDP传输客户端
 *
 * @author HKLLY
 * @date 2019-07-18
 */
public class EchoClient implements Runnable {
    private final String info;
    private final String host;
    private final int port;
    
    public EchoClient(String info, String host, int port) {
        this.info = info;
        this.host = host;
        this.port = port;
    }
    
    public static void main(String[] args) throws Exception {
        ClientUtils.connect(EchoClient.class, 10, "localhost", 9999);
    }
    
    @Override
    public void run() {
        init();
    }
    
    public void init() {
        //创建表示客户端的DatagramSocket
        try (DatagramSocket client = new DatagramSocket()) {
            //创建发送DatagramPocket
            byte[] data = info.getBytes();
            InetSocketAddress socketAddress = new InetSocketAddress(host, port);
            DatagramPacket sendPk = new DatagramPacket(data, data.length, socketAddress);
            //向服务器发送信息
            client.send(sendPk);
            
            //创建接受DatagramPocket
            byte[] feed = new byte[1024];
            DatagramPacket rcvPk = new DatagramPacket(feed, feed.length);
            //接收反馈
            client.receive(rcvPk);
            String feedStr = new String(feed, 0, rcvPk.getLength());

            System.out.println(feedStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
