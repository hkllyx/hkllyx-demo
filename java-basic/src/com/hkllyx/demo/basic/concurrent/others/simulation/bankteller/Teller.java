package com.hkllyx.demo.basic.concurrent.others.simulation.bankteller;

import java.util.concurrent.TimeUnit;

/**
 * 出纳员
 *
 * @author HKLLY
 * @date 2019/4/14
 */
public class Teller implements Runnable, Comparable<Teller> {
    private static int counter = 0;
    private final int id = counter++;
    /**
     * 服务的客户数
     */
    private int customerServed = 0;
    /**
     * 服务的客户线
     */
    private CustomerLine customerLine;
    /**
     * 是否正在服务
     */
    private boolean onServing = true;

    public Teller(CustomerLine customerLine) {
        this.customerLine = customerLine;
    }

    @Override
    public synchronized int compareTo(Teller o) {
        return Integer.compare(customerServed, o.customerServed);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 模拟服务一位客户
                Customer c = customerLine.take();
                TimeUnit.MILLISECONDS.sleep(c.getServiceTime());
                synchronized (this) {
                    // 服务数量+1
                    customerServed++;
                    // 如果不在服务，持续等待
                    while (!onServing) {
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println(this + " Interrupted");
        }
        System.out.println(this + " Terminating");
    }

    /**
     * 出纳员加入服务
     */
    public synchronized void serveCustomerLine() {
        assert !onServing : "Already serving: " + this;
        onServing = true;
        notifyAll();
    }

    /**
     * 出纳员退出服务
     */
    public synchronized void doSomethingElse() {
        customerServed = 0;
        onServing = false;
    }

    @Override
    public String toString() {
        return "Teller " + id;
    }

    public String toShortString() {
        return "T" + id;
    }
}
