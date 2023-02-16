package com.hkllyx.demo.basic.concurrent.others.simulation.distributingwork;

/**
 * 引擎相关机器人
 *
 * @author HKLLY
 * @date 2019/4/14
 */
public class EngineRobot extends AbstractRobot {
    public EngineRobot(RobotPool pool) {
        super(pool);
    }

    @Override
    protected void performService() {
        System.out.println(this + " installing engine");
        assembler.car().addEngine();
    }
}
