package com.hkllyx.demo.basic.io.net.tcp.client;

import com.hkllyx.demo.basic.io.net.ClientUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringJoiner;

/**
 * 模拟TCP传输客户端
 *
 * @author HKLLY
 * @date 2019-07-17
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

    private void init() {
        //创建客户端socket，指定服务器 IP 地址（或主机名）和端口号
        try (Socket client = new Socket(host, port);
                PrintWriter pw = new PrintWriter(client.getOutputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
            //向服务器发送信息
            pw.write(info);
            pw.flush();
            client.shutdownOutput();

            //获取服务器反馈的信息
            StringJoiner feed = new StringJoiner("\n");
            String line;
            while ((line = br.readLine()) != null) {
                feed.add(line);
            }

            System.out.println(feed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
