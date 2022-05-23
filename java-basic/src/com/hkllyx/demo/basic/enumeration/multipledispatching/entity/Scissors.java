package com.hkllyx.demo.basic.enumeration.multipledispatching.entity;

/**
 * 模拟剪刀
 *
 * @author HKLLY
 * @date 2019/4/8
 */
public class Scissors implements Item {

    /**
     * 其他实体和当前实体比
     *
     * @param item 与之对比的实体
     * @return 对比结果
     */
    @Override
    public Outcome compete(Item item) {
        return item.eval(this);
    }

    /**
     * 纸和当前实体比
     *
     * @param p 纸
     * @return 对比结果
     */
    @Override
    public Outcome eval(Paper p) {
        return Outcome.LOSE;
    }

    /**
     * 剪刀和当前实体比
     *
     * @param s 剪刀
     * @return 对比结果
     */
    @Override
    public Outcome eval(Scissors s) {
        return Outcome.DRAW;
    }

    /**
     * 石头和当前实体比
     *
     * @param r 石头
     * @return 对比结果
     */
    @Override
    public Outcome eval(Rock r) {
        return Outcome.WIN;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
