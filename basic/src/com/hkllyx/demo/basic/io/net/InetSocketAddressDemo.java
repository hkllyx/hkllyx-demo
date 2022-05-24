package com.hkllyx.demo.basic.io.net;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * 1）该类实现一个IP套接字地址(IP地址+端口号)，也可以是一对(主机名+端口号)，
 * 在这种情况下，将尝试解析主机名。如果解析失败，那么地址被称为未解析的（unresolved），但是仍然可以在某些情况下使用，
 * 比如通过代理连接。<br>
 * 2）它提供一个不可变的对象，Socket将其用于绑定、连接或作为返回值。<br>
 * 3）通配符（通常是0.0.0.0 or ::0）是一个特殊的本地IP地址。 它通常表示“任意”（any），并且只能用于bind()操作。<br>
 *
 * @author HKLLY
 * @date 2019-07-18
 */
public class InetSocketAddressDemo {

    public static void main(String[] args) throws UnknownHostException {
        //用IP地址为通配符地址（通过包可见方法InetAddress.anyLocalAddress()获取）指定端口号创建
        InetSocketAddress address = new InetSocketAddress(8080);
        System.out.println("new InetSocketAddress(8080) = " + address);
        //用指定主机名和端口号创建
        address = new InetSocketAddress("localhost", 8080);
        System.out.println("new InetSocketAddress(\"localhost\", 8080) = " + address);
        //用指定IP地址（InetAddress）和端口号创建
        address = new InetSocketAddress(InetAddress.getLocalHost(), 8080);
        System.out.println("new InetSocketAddress(" +
                "InetAddress.getLocalHost(), 8080) = " + address);
        System.out.println();
        
        //检查是否使用主机名解析成IP地址
        System.out.println("address.isUnresolved() = " + address.isUnresolved());
        //获取主机名，注意:如果使用文字IP地址创建地址，此方法可能触发名称服务反向查找。
        System.out.println("address.getHostName() = " + address.getHostName());
        //获取IP地址（InetAddress）
        System.out.println("address.getAddress() = " + address.getAddress());
        //获取端口号
        System.out.println("address.getPort() = " + address.getPort());
        //返回主机名，如果没有主机名，则返回地址的字符串形式。
        System.out.println("address.getHostString() = " + address.getHostString());
    }
}
