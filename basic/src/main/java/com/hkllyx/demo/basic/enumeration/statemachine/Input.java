package com.hkllyx.demo.basic.enumeration.statemachine;

import java.util.Random;

/**
 * 投币相关操作
 *
 * @author HKLLY
 * @date 2019/4/8
 */
public enum Input {
    /**
     * 投入金额
     */
    NICKEL(5),
    DIME(10),
    QUARTER(25),
    DOLLAR(100),
    /**
     * 商品价格
     */
    TOOTHPASTE(200),
    CHIPS(75),
    SODA(100),
    SOAP(50),
    /**
     * 放弃事务操作
     */
    ABORT_TRANSACTION {
        @Override
        public int amount() {
            throw new RuntimeException("ABORT.amount()");
        }
    },
    /**
     * 停止操作
     */
    STOP {
        @Override
        public int amount() {
            throw new RuntimeException("SHUTDOWN.amount()");
        }
    };

    private static final Random RANDOM = new Random(47);
    int value;

    Input(int value) {
        this.value = value;
    }

    Input() {
    }

    public static Input randomSelection() {
        //  Don't include STOP:
        return values()[RANDOM.nextInt(values().length - 1)];
    }

    int amount() {
        return value;
    }
}
