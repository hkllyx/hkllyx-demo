package com.hkllyx.demo.basic.io.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * 1）该类表示用于发送和接收Datagram的Socket（可以表示服务器和客户Socket）。<br>
 * 2）DatagramSocket是Datagram发送服务的发送点或接收点。<br>
 * 3）在DatagramSocket上发送或接收的每个Datagram都分别寻址和路由。<br>
 * 4）从一台机器发送到另一台机器的多个包可能以不同的方式路由，并且可能以任意顺序到达。<br>
 *
 * @author HKLLY
 * @date 2019-07-18
 */
public class DatagramSocketDemo {
    
    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getLocalHost();
        InetSocketAddress socketAddress = new InetSocketAddress(address, 8080);
        
        //1）创建一个DatagramSocket，并将其绑定到本地主机上的任何可用端口。
        //Socket将绑定到InetAddress.anyLocalAddress()地址，IP地址内核由选择。
        DatagramSocket socket = new DatagramSocket();
        System.out.println("new DatagramSocket() = " + socket);
        //2）指定端口号
        socket = new DatagramSocket(8080);
        System.out.println("new DatagramSocket(8080) = " + socket);
        //3）指定SocketAddress（IP地址和端口号）
        socket = new DatagramSocket(socketAddress);
        System.out.println("new DatagramSocket(socketAddress) = " + socket);
        //指定IP地址和端口号
        socket = new DatagramSocket(8080, address);
        System.out.println("new DatagramSocket(8080, address) = " + socket);
        System.out.println();
        
        //发送DatagramPacket
        socket.send(new DatagramPacket(new byte[]{}, 0));
        //接收DatagramPacket
        socket.receive(new DatagramPacket(new byte[]{}, 0));
    
        //Socket是否绑定
        System.out.println("socket.isBound() = " + socket.isBound());
        //Socket是否关闭
        System.out.println("socket.isClosed() = " + socket.isClosed());
        //Socket是否连接
        System.out.println("socket.isConnected() = " + socket.isConnected());
        System.out.println();
    
        //获取ip地址
        System.out.println("socket.getInetAddress() = " + socket.getInetAddress());
        //获取端口号
        System.out.println("socket.getPort() = " + socket.getPort());
        //获取本地端口号
        System.out.println("socket.getLocalPort() = " + socket.getLocalPort());
        //获取SocketChannel
        System.out.println("socket.getChannel() = " + socket.getChannel());
        //获取本地SocketAddress
        System.out.println("socket.getLocalSocketAddress() = " + socket.getLocalSocketAddress());
        //获取远程SocketAddress
        System.out.println("socket.getRemoteSocketAddress() = " + socket.getRemoteSocketAddress());
    
        //测试是否启用了java.net.SocketOptions中的选项
        System.out.println("socket.getReuseAddress() = " + socket.getReuseAddress());
        System.out.println("socket.getReceiveBufferSize() = " + socket.getReceiveBufferSize());
        System.out.println("socket.getSendBufferSize() = " + socket.getSendBufferSize());
        System.out.println("socket.getSoTimeout() = " + socket.getSoTimeout());
        System.out.println("socket.getTrafficClass() = " + socket.getTrafficClass());
        System.out.println("socket.getBroadcast() = " + socket.getBroadcast());
        System.out.println();
    
        //设置java.net.SocketOptions
        socket.setReuseAddress(true);
        socket.setReceiveBufferSize(1024);
        socket.setSendBufferSize(1024);
        socket.setSoTimeout(1000);
        socket.setTrafficClass(0);
        socket.setBroadcast(true);
    }
}
