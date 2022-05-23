package com.hkllyx.demo.basic.io.net;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * 1）URL表示统一资源定位符，指向万维网上“资源”的指针。<br>
 * 2）资源可以是文件或目录这样简单的东西，也可以是对更复杂的对象的引用，例如对数据库或搜索引擎的查询。<br>
 *
 * @author HKLLY
 * @date 2019-07-17
 */
public class UrlDemo {

    public static void main(String[] args) {
        try {
            //1）根据一个字符串形式的URL，来构建URL对象。
            URL url = new URL("http://localhost:8080/file");
            System.out.println(url);
            //2）根据协议、主机名、文件来构造一个URL。使用该协议默认的端口，并且file参数应当以斜线开头，包括文件路径、文件名称和片段。
            url = new URL("http", "localhost", "/file");
            System.out.println(url);
            //3）根据协议、主机名、端口、文件来构造一个URL。
            url = new URL("http", "localhost", 8080, "/file");
            System.out.println(url);
            //4）根据 协议、主机名、端口、文件和URLStreamHandler来构造一个URL。
            // URLStreamHandler：抽象类，主要是用来读取指定的资源，并返回该资源的一个流。
            url = new URL("http", "localhost", 8080, "/file", null);
            System.out.println(url);
            //5）根据一个基础URL和一个相对URL来构建一个绝对URL。
            // 结果将基础URL的协议之后的内容（文件）替换成相对URL，而不是在其后追加
            URL url1 = new URL(url, "/file1");
            System.out.println(url1);
            //6）根据一个基础URL和一个相对URL来构建一个绝对URL，并传入一个URLStreamHandler对象。
            url1 = new URL(url, "/file1", null);
            System.out.println(url1);
            System.out.println();
            
            //获取相关属性
            url = new URL("https://docs.oracle.com/javase/8/docs/api/java/net/URL.html");
            //获取协议
            System.out.println("url.getProtocol() = " + url.getProtocol());
            //获取主机名
            System.out.println("url.getHost() = " + url.getHost());
            //获取端口
            System.out.println("url.getPort() = " + url.getPort());
            //获取URL的协议的默认端口
            System.out.println("url.getDefaultPort() = " + url.getDefaultPort());
            //获取文件全名（包括属性和锚点）
            System.out.println("url.getFile() = " + url.getFile());
            //获取文件路径（不包括属性和锚点）
            System.out.println("url.getPath() = " + url.getPath());
            //获取查询部分（属性）
            System.out.println("url.getQuery() = " + url.getQuery());
            //获取锚点
            System.out.println("url.getRef() = " + url.getRef());
            //获取URL的权限部分
            System.out.println("url.getAuthority() = " + url.getAuthority());
            //获取用户信息
            System.out.println("url.getUserInfo() = " + url.getUserInfo());
            //获取URL的内容
            System.out.println("url.getContent() = " + url.getContent());
            //参数是这个URL的内容对象，它是类数组中指定的类型的第一个匹配到的项。
            // 如果不支持所请求的任何类型，则为null。
            System.out.println("url.getContent(new Class[]{FilterInputStream.class}) = "
                    + url.getContent(new Class[]{FilterInputStream.class}));
            System.out.println();
            
            //获取网页内容
            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    url.openStream(), StandardCharsets.UTF_8));
                    PrintWriter out = new PrintWriter("src/main/resources/url-demo.html")) {
                String line;
                while ((line = in.readLine()) != null) {
                    out.write(line);
                    out.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
