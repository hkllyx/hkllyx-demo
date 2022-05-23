package com.hkllyx.demo.basic.io.net;

import com.hkllyx.demo.basic.concurrent.util.ExecutorUtils;
import com.hkllyx.demo.basic.generic.generator.CountingGenerator;
import com.hkllyx.demo.basic.generic.generator.RandomGenerator;

import java.lang.reflect.Constructor;
import java.util.concurrent.ExecutorService;

/**
 * 用于测试TCP/UDP客户端连接服务器
 *
 * @author HKLLY
 * @date 2019-07-20
 */
public class ClientUtils {
    
    /**
     * 模拟多个客户端连接值服务器
     *
     * @param type     客户端类型
     * @param nClients 客户端数量
     * @param host     远程IP地址
     * @param port     远程端口号
     */
    public static void connect(Class<? extends Runnable> type, int nClients,
                               String host, int port) throws Exception {
        ExecutorService pool = ExecutorUtils.newFixedThreadPool(nClients);
        CountingGenerator unGen = new CountingGenerator();
        RandomGenerator pwGen = new RandomGenerator();
        Constructor<? extends Runnable> constructor = type.getConstructor(String.class, String.class, int.class);
        for (int i = 0; i < nClients; i++) {
            String info = "id = " + i + ", username = " + unGen.nextString() + ", password = " + pwGen.nextString();
            Runnable client = constructor.newInstance(info, host, port);
            pool.execute(client);
        }
        pool.shutdown();
    }
}
