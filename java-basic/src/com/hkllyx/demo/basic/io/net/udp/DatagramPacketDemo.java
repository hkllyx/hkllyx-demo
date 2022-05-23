package com.hkllyx.demo.basic.io.net.udp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * 1）DatagramPacket用于实现无连接的包传递服务（不实现连接，用于保存数据报包）。<br>
 * 2）每条消息仅根据包中包含的信息从一台机器路由到另一台机器。<br>
 * 3）从一台机器发送到另一台机器的多个包可能以不同的方式路由，并且可能以任意顺序到达。<br>
 * 4）包交付不保证（接收端不会反馈是否接收成功）。<br>
 *
 * @author HKLLY
 * @date 2019-07-18
 */
public class DatagramPacketDemo {
    
    public static void main(String[] args) throws UnknownHostException {
        byte[] data = "DatagramPacket".getBytes();
        InetAddress address = InetAddress.getLocalHost();
        InetSocketAddress socketAddress = new InetSocketAddress(address, 8080);
        
        //1）创建一个接受指定byte[]指定长度的DatagramPacket，长度应小于或等于byte[]的长度
        DatagramPacket packet = new DatagramPacket(data, data.length);
        System.out.println("new DatagramPacket(data, data.length) = " + packet);
        //2）指定byte[]的偏移
        packet = new DatagramPacket(data, 8, data.length - 8);
        System.out.println("new DatagramPacket(data, 8, data.length - 8) = " + packet);
        //3）创建时DatagramPacket指定SocketAddress
        packet = new DatagramPacket(data, data.length, socketAddress);
        System.out.println("new DatagramPacket(data, data.length, socketAddress) = " + packet);
        //4）
        packet = new DatagramPacket(data, 8, data.length - 8, socketAddress);
        System.out.println("new DatagramPacket(data, 8, data.length - 8, socketAddress) = " + packet);
        //5）创建时DatagramPacket指定InetAddress和端口号
        packet = new DatagramPacket(data, data.length, address, 8080);
        System.out.println("new DatagramPacket(data, data.length, address, 8080) = " + packet);
        //6）
        packet = new DatagramPacket(data, 8, data.length - 8, address, 8080);
        System.out.println("new DatagramPacket(data, 8, data.length - 8, address, 8080) = " + packet);
        System.out.println();
    
        //getter
        System.out.println("new String(packet.getData()) = " + new String(packet.getData()));
        System.out.println("packet.getOffset() = " + packet.getOffset());
        System.out.println("packet.getLength() = " + packet.getLength());
        System.out.println("packet.getSocketAddress() = " + packet.getSocketAddress());
        System.out.println("packet.getAddress() = " + packet.getAddress());
        System.out.println("packet.getPort() = " + packet.getPort());
        System.out.println();
        
        //setter
        packet.setData(data);
        packet.setData(data, 8, data.length - 8);
        packet.setLength(data.length);
        packet.setSocketAddress(socketAddress);
        packet.setAddress(address);
        packet.setPort(8080);
    }
}
