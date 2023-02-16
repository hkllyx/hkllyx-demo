package com.hkllyx.demo.basic.concurrent.others.simulation.distributingwork;

import com.hkllyx.demo.basic.concurrent.util.ExecutorUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author HKLLY
 * @date 2019/4/14
 */
public class CarBuilderDemo {

    public static void main(String[] args) throws Exception {
        LinkedBlockingQueue<Car> chassisQueue = new LinkedBlockingQueue<>(),
                finishingQueue = new LinkedBlockingQueue<>();
        ExecutorService exec = ExecutorUtils.newCachedThreadPool();
        // 创建机器人池
        RobotPool robotPool = new RobotPool();
        // 运行机器人
        exec.execute(new EngineRobot(robotPool));
        exec.execute(new DriveTrainRobot(robotPool));
        exec.execute(new WheelRobot(robotPool));
        // 运行组装任务
        exec.execute(new CarAssembler(chassisQueue, finishingQueue, robotPool));
        // 报告组装情况
        exec.execute(new Reporter(finishingQueue));
        // 运行底盘生成任务
        exec.execute(new ChassisBuilder(chassisQueue));
        TimeUnit.SECONDS.sleep(7);
        exec.shutdownNow();
    }
}
