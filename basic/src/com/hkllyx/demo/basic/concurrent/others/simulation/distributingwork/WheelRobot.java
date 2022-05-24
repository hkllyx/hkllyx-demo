package com.hkllyx.demo.basic.concurrent.others.simulation.distributingwork;

/**
 * @author HKLLY
 * @date 2019/4/14
 */
public class WheelRobot extends AbstractRobot {
    public WheelRobot(RobotPool pool) {
        super(pool);
    }

    @Override
    protected void performService() {
        System.out.println(this + " installing Wheels");
        assembler.car().addWheels();
    }
}