package com.hkllyx.demo.basic.enumeration.multipledispatching;

import com.hkllyx.demo.basic.enumeration.multipledispatching.entity.Competitor;
import com.hkllyx.demo.basic.enumeration.multipledispatching.entity.Outcome;

/**
 * @author HKLLY
 * @date 2019/4/8
 */
public enum RoShamBo1 implements Competitor<RoShamBo1> {
    /**
     * 括号中的值表示和纸、剪刀、石头比的结果
     */
    PAPER(Outcome.DRAW, Outcome.LOSE, Outcome.WIN),
    SCISSORS(Outcome.WIN, Outcome.DRAW, Outcome.LOSE),
    ROCK(Outcome.LOSE, Outcome.WIN, Outcome.DRAW);

    private final Outcome vPaper;
    private final Outcome vScissors;
    private final Outcome vRock;

    /**
     * 设置每个字段
     *
     * @param paper    对比纸的结果
     * @param scissors 对比剪刀的结果
     * @param rock     对比石头的结果
     */
    RoShamBo1(Outcome paper, Outcome scissors, Outcome rock) {
        vPaper = paper;
        vScissors = scissors;
        vRock = rock;
    }

    public static void main(String[] args) {
        RoShamBoUtil.play(RoShamBo1.class, 10);
    }

    @Override
    public Outcome compete(RoShamBo1 opponent) {
        switch (opponent) {
            case PAPER:
                return vPaper;
            case SCISSORS:
                return vScissors;
            case ROCK:
                return vRock;
            default:
                return null;
        }
    }
}
