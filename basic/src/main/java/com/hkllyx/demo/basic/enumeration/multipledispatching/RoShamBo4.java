package com.hkllyx.demo.basic.enumeration.multipledispatching;

import com.hkllyx.demo.basic.enumeration.multipledispatching.entity.Competitor;
import com.hkllyx.demo.basic.enumeration.multipledispatching.entity.Outcome;

import java.util.EnumMap;

/**
 * @author HKLLY
 * @date 2019/4/8
 */
public enum RoShamBo4 implements Competitor<RoShamBo4> {
    /**
     *
     */
    PAPER,
    SCISSORS,
    ROCK;

    /**
     * 创建枚举映射，由rsb获取对比结果映射
     */
    static EnumMap<RoShamBo4, EnumMap<RoShamBo4, Outcome>> table =
            new EnumMap<>(RoShamBo4.class);

    static {
        for (RoShamBo4 rsb : RoShamBo4.values()) {
            table.put(rsb, new EnumMap<>(RoShamBo4.class));
        }
        initRow(PAPER, Outcome.DRAW, Outcome.LOSE, Outcome.WIN);
        initRow(SCISSORS, Outcome.WIN, Outcome.DRAW, Outcome.LOSE);
        initRow(ROCK, Outcome.LOSE, Outcome.WIN, Outcome.DRAW);
    }

    static void initRow(RoShamBo4 rsb,
            Outcome vPaper,
            Outcome vScissors,
            Outcome vRock) {
        EnumMap<RoShamBo4, Outcome> row = RoShamBo4.table.get(rsb);
        row.put(RoShamBo4.PAPER, vPaper);
        row.put(RoShamBo4.SCISSORS, vScissors);
        row.put(RoShamBo4.ROCK, vRock);
    }

    /**
     * 3*3 table, column for this, row for opponent, value is the result.
     */
    @Override
    public Outcome compete(RoShamBo4 opponent) {
        return table.get(this).get(opponent);
    }

    public static void main(String[] args) {
        RoShamBoUtil.play(RoShamBo4.class, 10);
    }
}