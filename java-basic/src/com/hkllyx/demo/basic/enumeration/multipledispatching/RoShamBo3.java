package com.hkllyx.demo.basic.enumeration.multipledispatching;

import com.hkllyx.demo.basic.enumeration.multipledispatching.entity.Competitor;
import com.hkllyx.demo.basic.enumeration.multipledispatching.entity.Outcome;

/**
 * @author HKLLY
 * @date 2019/4/8
 */
public enum RoShamBo3 implements Competitor<RoShamBo3> {
    /**
     * 石头
     */
    ROCK {
        @Override
        public Outcome compete(RoShamBo3 opponent) {
            return compete(SCISSORS, opponent);
        }
    },
    SCISSORS {
        @Override
        public Outcome compete(RoShamBo3 opponent) {
            return compete(PAPER, opponent);
        }
    },
    PAPER {
        @Override
        public Outcome compete(RoShamBo3 opponent) {
            return compete(ROCK, opponent);
        }
    };

    public static void main(String[] args) {
        RoShamBoUtil.play(RoShamBo3.class, 10);
    }

    /**
     * 对比
     *
     * @param loser    输给当前实体的实体
     * @param opponent 对手
     * @return 对比结果
     */
    Outcome compete(RoShamBo3 loser, RoShamBo3 opponent) {
        // 若对手同当前，返回DRAW，为loser则返回WIN，否则为LOSE
        if (opponent == this) {
            return Outcome.DRAW;
        } else if (opponent == loser) {
            return Outcome.WIN;
        } else {
            return Outcome.LOSE;
        }
    }
}
