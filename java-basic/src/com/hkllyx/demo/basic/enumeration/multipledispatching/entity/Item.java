package com.hkllyx.demo.basic.enumeration.multipledispatching.entity;

/**
 * 实体
 *
 * @author HKLLY
 * @date 2019/4/8
 */
public interface Item {
    /**
     * 其他实体和当前实体比
     *
     * @param item 与之对比的实体
     * @return 对比结果
     */
    Outcome compete(Item item);

    /**
     * 纸和当前实体比
     *
     * @param p 纸
     * @return 对比结果
     */
    Outcome eval(Paper p);

    /**
     * 剪刀和当前实体比
     *
     * @param s 剪刀
     * @return 对比结果
     */
    Outcome eval(Scissors s);

    /**
     * 石头和当前实体比
     *
     * @param r 石头
     * @return 对比结果
     */
    Outcome eval(Rock r);
}
