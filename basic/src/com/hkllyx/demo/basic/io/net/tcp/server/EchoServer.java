package com.hkllyx.demo.basic.io.net.tcp.server;

import com.hkllyx.demo.basic.concurrent.util.ExecutorUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringJoiner;
import java.util.concurrent.ExecutorService;

/**
 * 模拟TCP传输服务器端
 *
 * @author HKLLY
 * @date 2019-07-17
 */
public class EchoServer {
    public static ExecutorService pool;

    public EchoServer(int port) throws IOException {
        pool = ExecutorUtils.newCachedThreadPool();
        init(port);
    }

    public static void main(String[] args) throws IOException {
        new EchoServer(9999);
    }

    public void init(int port) throws IOException {
        //创建服务器ServerSocket，指定监听的端口号
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Waiting for clients...");
            while (true) {
                //监听，等待客户端连接
                Socket client = server.accept();
                //为客户端创建一条线程执行相应工作
                pool.execute(new ClientHandleTask(client));
            }
        }
    }

    /**
     * 客户端处理线程，负责接受客户端信息和向客户端反馈信息。
     */
    private static class ClientHandleTask implements Runnable {
        private final Socket client;

        ClientHandleTask(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter pw = new PrintWriter(client.getOutputStream())) {
                //获取客户端消息
                StringJoiner info = new StringJoiner("\n");
                String line;
                while ((line = br.readLine()) != null) {
                    info.add(line);
                }
                String infoStr = info.toString();
                System.out.println(infoStr);

                //向客户端反馈信息
                pw.write(infoStr);
                pw.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
