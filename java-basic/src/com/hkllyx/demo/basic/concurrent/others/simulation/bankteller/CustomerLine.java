package com.hkllyx.demo.basic.concurrent.others.simulation.bankteller;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 客户线，实现ArrayBlockingQueue，线程安全
 *
 * @author HKLLY
 * @date 2019/4/14
 */
public class CustomerLine extends ArrayBlockingQueue<Customer> {

    public CustomerLine(int capacity) {
        super(capacity);
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "[Empty]";
        }
        StringBuilder sb = new StringBuilder();
        for (Customer c : this) {
            sb.append(c);
        }
        return sb.toString();
    }
}
