package com.hkllyx.demo.basic.concurrent.others.simulation.bankteller;

/**
 * 客户
 *
 * @author HKLLY
 * @date 2019/4/14
 */
public class Customer {
    private final int serviceTime;

    public Customer(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    /**
     * 获取服务时间
     *
     * @return 服务时间
     */
    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "[" + serviceTime + "]";
    }
}
