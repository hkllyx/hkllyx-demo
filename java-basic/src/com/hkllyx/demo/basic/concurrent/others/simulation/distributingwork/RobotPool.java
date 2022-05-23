package com.hkllyx.demo.basic.concurrent.others.simulation.distributingwork;

import java.util.HashSet;
import java.util.Set;

/**
 * 机器人池
 *
 * @author HKLLY
 * @date 2019/4/14
 */
public class RobotPool {
    private Set<AbstractRobot> pool = new HashSet<>();

    public synchronized void add(AbstractRobot r) {
        pool.add(r);
        notifyAll();
    }

    /**
     * 雇佣机器人
     *
     * @param robotType 机器人类型
     * @param d         组装间
     * @throws InterruptedException ex
     */
    public synchronized void hire(Class<? extends AbstractRobot> robotType,
            CarAssembler d) throws InterruptedException {
        for (AbstractRobot r : pool) {
            if (r.getClass().equals(robotType)) {
                pool.remove(r);
                r.assignAssembler(d);
                r.engage();
                return;
            }
        }
        wait();
        hire(robotType, d);
    }

    /**
     * 释放机器人
     *
     * @param r 机器人
     */
    public synchronized void release(AbstractRobot r) {
        add(r);
    }
}
