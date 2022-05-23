package com.hkllyx.demo.basic.enumeration.multipledispatching.entity;

/**
 * 模拟石头
 *
 * @author HKLLY
 * @date 2019/4/8
 */
public class Rock implements Item {

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
        return Outcome.WIN;
    }

    /**
     * 剪刀和当前实体比
     *
     * @param s 剪刀
     * @return 对比结果
     */
    @Override
    public Outcome eval(Scissors s) {
        return Outcome.LOSE;
    }

    /**
     * 石头和当前实体比
     *
     * @param r 石头
     * @return 对比结果
     */
    @Override
    public Outcome eval(Rock r) {
        return Outcome.DRAW;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
