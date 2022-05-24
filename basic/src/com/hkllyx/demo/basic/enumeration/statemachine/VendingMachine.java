package com.hkllyx.demo.basic.enumeration.statemachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static com.hkllyx.demo.basic.enumeration.statemachine.Input.*;

/**
 * 自动售货机运行机制
 *
 * @author HKLLY
 * @date 2019/4/8
 */
public class VendingMachine {
    /**
     * 初始化机器状态
     */
    private static State state = State.RESTING;
    /**
     * 投币总金额
     */
    private static int amount = 0;
    /**
     * 当前操作
     */
    private static Input selection = null;

    public static void main(String[] args) {
        ArrayList<Input> inputs = new ArrayList<>(Arrays.asList(
                QUARTER, QUARTER, QUARTER, CHIPS,
                DOLLAR, DOLLAR, TOOTHPASTE,
                QUARTER, DIME, ABORT_TRANSACTION,
                QUARTER, DIME, SODA,
                QUARTER, DIME, NICKEL, SODA,
                ABORT_TRANSACTION,
                STOP));
        VendingMachine.run(inputs.iterator());
    }

    /**
     * 机器运行
     *
     * @param iterator 运行中的一系列输入
     */
    public static void run(Iterator<Input> iterator) {
        while (state != State.TERMINAL && iterator.hasNext()) {
            state.next(iterator.next());
            while (state.isTransient) {
                state.next();
            }
            state.output();
        }
    }

    /**
     * 状态持续
     */
    enum StateDuration {
        /**
         * 瞬时
         */
        TRANSIENT
    }

    /**
     * 机器运行状态
     */
    enum State {
        /**
         * 休息
         */
        RESTING {
            @Override
            void next(Input input) {
                switch (Category.categorize(input)) {
                    // 如果分类为金额，计算投币金额，设置下一状态为加钱
                    case MONEY:
                        amount += input.amount();
                        state = ADDING_MONEY;
                        break;
                    // 如果分类是关机，则设置状态下一为停止运行
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        /**
         * 继续投币
         */
        ADDING_MONEY {
            @Override
            void next(Input input) {
                switch (Category.categorize(input)) {
                    // 如果分类为金额，表示继续投币，计算总金额
                    case MONEY:
                        amount += input.amount();
                        break;
                    // 如果分类为选择商品，计算投入金额是否足够。不足设置下一状态为继续投币，
                    // 否则设置下一状态为执行
                    case ITEM_SELECTION:
                        selection = input;
                        if (amount < selection.amount()) {
                            System.out.println("Insufficient money for " + selection);
                            state = ADDING_MONEY;
                        } else {
                            state = DISPENSING;
                        }
                        break;
                    // 如果分类为放弃事务，则退回投入的金额
                    case QUIT_TRANSACTION:
                        state = GIVING_CHANGE;
                        break;
                    // 如果分类为关机，则设置状态为结束
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        /**
         * 执行
         */
        DISPENSING(StateDuration.TRANSIENT) {
            @Override
            void next() {
                // 弹出商品，计算找零金额并设置下一状态为找零
                System.out.println("here is your " + selection);
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }
        },
        /**
         * 找零
         */
        GIVING_CHANGE(StateDuration.TRANSIENT) {
            @Override
            void next() {
                // 退回金额，设置下一状态为休息
                if (amount > 0) {
                    System.out.println("Your change: " + amount);
                    amount = 0;
                }
                state = RESTING;
            }
        },
        /**
         * 停止运行
         */
        TERMINAL {
            @Override
            void output() {
                // 关闭机器
                System.out.println("Halted");
            }
        };

        /**
         * 是否为瞬时状态
         */
        private boolean isTransient = false;

        State() {
        }

        State(StateDuration trans) {
            isTransient = true;
        }

        /**
         * 非瞬时状态的下一个状态
         *
         * @param input 下一状态的输入
         */
        void next(Input input) {
            throw new RuntimeException("Only call " +
                    "next(Input input) for non-transient states");
        }

        /**
         * 瞬时状态的下一个状态
         */
        void next() {
            throw new RuntimeException("Only call next() for " +
                    "StateDuration.TRANSIENT states");
        }

        /**
         * 输出当前状态下的结果
         */
        void output() {
            System.out.println(amount);
        }
    }
}
