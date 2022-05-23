package com.hkllyx.demo.basic.concurrent.others.simulation.bankteller;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 出纳员管理员，调整出纳员是否服务
 *
 * @author HKLLY
 * @date 2019/4/14
 */
public class TellerManager implements Runnable {
    private static Random random = new Random(47);
    private ExecutorService pool;
    /**
     * 管理的客户线
     */
    private CustomerLine customerLine;
    /**
     * 正在服务的出纳员列队
     */
    private PriorityBlockingQueue<Teller>
            workingTellers = new PriorityBlockingQueue<>();
    /**
     * 不在服务的出纳员列队
     */
    private Queue<Teller> tellersDoingOtherThings = new LinkedList<>();
    /**
     * 调整周期
     */
    private int adjustmentPeriod;

    public TellerManager(ExecutorService pool,
            CustomerLine customerLine, int adjustmentPeriod) {
        this.pool = pool;
        this.customerLine = customerLine;
        this.adjustmentPeriod = adjustmentPeriod;
        Teller teller = new Teller(customerLine);
        pool.execute(teller);
        workingTellers.add(teller);
    }

    public void adjustTellerNumber() {
        Teller teller;
        // 如果有客户而无出纳员，或者客户数量是出纳员的2倍，调整出纳员数量
        while ((customerLine.size() > 0 && workingTellers.size() == 0) ||
                (customerLine.size() / workingTellers.size() >= 2)) {
            // 如果存在不在服务的出纳员，调出去服务。否则重新雇佣出纳员
            if (tellersDoingOtherThings.size() > 0) {
                teller = tellersDoingOtherThings.remove();
                teller.serveCustomerLine();
                workingTellers.offer(teller);
            } else {
                teller = new Teller(customerLine);
                pool.execute(teller);
                workingTellers.add(teller);
            }
        }
        // 如果客户数量少于出纳员，将出纳员调出服务
        while (workingTellers.size() > 0 &&
                (customerLine.size() < workingTellers.size())) {
            teller = workingTellers.poll();
            teller.doSomethingElse();
            tellersDoingOtherThings.offer(teller);
        }
    }

    @Override
    public String toString() {
        return "TellerManager";
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellerNumber();
                System.out.print(customerLine + "{ ");
                for (Teller teller : workingTellers) {
                    System.out.print(teller.toShortString() + " ");
                }
                System.out.println("}");
            }
        } catch (InterruptedException e) {
            System.out.println(this + " Interrupted");
        }
        System.out.println(this + " Terminating");
    }
}
