package com.hkllyx.demo.basic.concurrent.others.simulation.distributingwork;

/**
 * 驱动相关机器人
 *
 * @author HKLLY
 * @date 2019/4/14
 */
public class DriveTrainRobot extends AbstractRobot {
    public DriveTrainRobot(RobotPool pool) {
        super(pool);
    }

    @Override
    protected void performService() {
        System.out.println(this + " installing DriveTrain");
        assembler.car().addDriveTrain();
    }
}
