package com.hkllyx.demo.basic.enumeration.multipledispatching.entity;

/**
 * 模拟布
 *
 * @author HKLLY
 * @date 2019/4/8
 */
public class Paper implements Item {

    /**
     * 其他实体和当前实体比
     *
     * @param item 与之对比的实体
     * @return 对比结果
     */
    @Override
    public Outcome compete(Item item) {
        // 无法确认item的类型，但确定this，倒置比较
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
        return Outcome.DRAW;
    }

    /**
     * 剪刀和当前实体比
     *
     * @param s 剪刀
     * @return 对比结果
     */
    @Override
    public Outcome eval(Scissors s) {
        return Outcome.WIN;
    }

    /**
     * 石头和当前实体比
     *
     * @param r 石头
     * @return 对比结果
     */
    @Override
    public Outcome eval(Rock r) {
        return Outcome.LOSE;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
