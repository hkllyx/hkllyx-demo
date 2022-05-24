package com.hkllyx.demo.basic.enumeration.multipledispatching;

import com.hkllyx.demo.basic.enumeration.multipledispatching.entity.Competitor;
import com.hkllyx.demo.basic.enumeration.multipledispatching.entity.Outcome;

/**
 * @author HKLLY
 * @date 2019/4/8
 */
public enum RoShamBo5 implements Competitor<RoShamBo5> {
    /**
     *
     */
    PAPER,
    SCISSORS,
    ROCK;

    /**
     * 行表示第一个（纸剪刀石头），
     * 列表示第二个（纸剪刀石头）。
     * 对应元素表示对比结果
     */
    private static final Outcome[][] TABLE = {
            {Outcome.DRAW, Outcome.LOSE, Outcome.WIN},
            {Outcome.WIN, Outcome.DRAW, Outcome.LOSE},
            {Outcome.LOSE, Outcome.WIN, Outcome.DRAW}};

    public static void main(String[] args) {
        RoShamBoUtil.play(RoShamBo5.class, 10);
    }

    @Override
    public Outcome compete(RoShamBo5 opponent) {
        return TABLE[this.ordinal()][opponent.ordinal()];
    }
}
