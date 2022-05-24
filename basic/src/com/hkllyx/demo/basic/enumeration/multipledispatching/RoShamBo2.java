package com.hkllyx.demo.basic.enumeration.multipledispatching;

import com.hkllyx.demo.basic.enumeration.multipledispatching.entity.Competitor;
import com.hkllyx.demo.basic.enumeration.multipledispatching.entity.Outcome;

/**
 * 模拟剪刀石头布
 * 利用枚举,
 *
 * @author HKLLY
 * @date 2019/4/8
 */
public enum RoShamBo2 implements Competitor<RoShamBo2> {
    /**
     *
     */
    PAPER {
        @Override
        public Outcome compete(RoShamBo2 it) {
            switch (it) {
                case PAPER:
                    return Outcome.DRAW;
                case SCISSORS:
                    return Outcome.LOSE;
                default:
                    return Outcome.WIN;
            }
        }
    },
    SCISSORS {
        @Override
        public Outcome compete(RoShamBo2 it) {
            switch (it) {
                case PAPER:
                    return Outcome.WIN;
                case SCISSORS:
                    return Outcome.DRAW;
                default:
                    return Outcome.LOSE;
            }
        }
    },
    ROCK {
        @Override
        public Outcome compete(RoShamBo2 opponent) {
            switch (opponent) {
                case PAPER:
                    return Outcome.LOSE;
                case SCISSORS:
                    return Outcome.WIN;
                default:
                    return Outcome.DRAW;
            }
        }
    };

    public static void main(String[] args) {
        RoShamBoUtil.play(RoShamBo2.class, 10);
    }

    @Override
    public abstract Outcome compete(RoShamBo2 it);
}