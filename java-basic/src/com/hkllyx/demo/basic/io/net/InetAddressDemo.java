package com.hkllyx.demo.basic.io.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * 1）表示一个Internet协议(IP)地址<br>
 * 2）InetAddress的实例由IP地址和可能的相应主机名组成（取决于它是使用主机名构造还是已经完成反向主机名解析）<br>
 *
 * @author HKLLY
 * @date 2019-07-17
 */
public class InetAddressDemo {

    public static void main(String[] args) throws UnknownHostException {
        //1）用InetAddress.getLocalHost()获取InetAddress对象
        InetAddress ipAddr = InetAddress.getLocalHost();
        //获取主机名
        String hostName = ipAddr.getHostName();
        //获取完全限定主机名
        String canonicalHostName = ipAddr.getCanonicalHostName();
        //获取字符串形式的ip地址
        String hostAddress = ipAddr.getHostAddress();
        //获取byte数组形式的ip地址
        byte[] address = ipAddr.getAddress();
        System.out.println("ipAddr = " + ipAddr);
        System.out.println("hostName = " + hostName);
        System.out.println("canonicalHostName = " + canonicalHostName);
        System.out.println("hostAddress = " + hostAddress);
        System.out.println("address = " + Arrays.toString(address));
        //2）用主机名和byte数组形式的ip地址获取InetAddress对象
        ipAddr = InetAddress.getByAddress(hostName, address);
        System.out.println("InetAddress.getByAddress(hostName, address) = " + ipAddr);
        //3）用主机名获取所有为该主机名的InetAddress对象数组
        InetAddress[] hosts = InetAddress.getAllByName(hostName);
        System.out.println("InetAddress.getAllByName(hostName) = "
                + Arrays.toString(hosts));
        //4）用主机名获取一个InetAddress对象
        ipAddr = InetAddress.getByName(hostName);
        System.out.println("InetAddress.getByName(hostName) = " + ipAddr);
        //5）用字符串形式的ip地址作主机名获得InetAddress对象
        ipAddr = InetAddress.getByName(hostAddress);
        System.out.println("InetAddress.getByName(hostAddress) = " + ipAddr);
        //6）用byte数组形式的ip地址数组获得InetAddress对象
        ipAddr = InetAddress.getByAddress(address);
        System.out.println("InetAddress.getByAddress(address) = " + ipAddr);
        System.out.println();
    
        //判断ip地址类型
        System.out.println("ipAddr.isAnyLocalAddress() = " + ipAddr.isAnyLocalAddress());
        System.out.println("ipAddr.isLinkLocalAddress() = " + ipAddr.isLinkLocalAddress());
        System.out.println("ipAddr.isLoopbackAddress() = " + ipAddr.isLoopbackAddress());
        System.out.println("ipAddr.isSiteLocalAddress() = " + ipAddr.isSiteLocalAddress());
        System.out.println("ipAddr.isMCGlobal() = " + ipAddr.isMCGlobal());
        System.out.println("ipAddr.isMCNodeLocal() = " + ipAddr.isMCNodeLocal());
        System.out.println("ipAddr.isMCLinkLocal() = " + ipAddr.isMCLinkLocal());
        System.out.println("ipAddr.isMCSiteLocal() = " + ipAddr.isMCSiteLocal());
        System.out.println("ipAddr.isMCOrgLocal() = " + ipAddr.isMCOrgLocal());
        System.out.println("ipAddr.isMulticastAddress() = " + ipAddr.isMulticastAddress());
        System.out.println();
    
        //判断ip地址是否可用（可用地址可能被防火墙拦截等导致不可用）
        try {
            //参数呼叫中止前的时间（以毫秒为单位）
            System.out.println("ipAddr.isReachable(1000) = " + ipAddr.isReachable(3000));
            //参数为将通过其进行测试的NetworkInterface，或对任何接口为null、
            // 要尝试的最大跃点数或默认值为0，呼叫中止前的时间（以毫秒为单位）
            System.out.println("ipAddr.isReachable(null, 0, 10000) = "
                    + ipAddr.isReachable(null, 0, 3000));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
