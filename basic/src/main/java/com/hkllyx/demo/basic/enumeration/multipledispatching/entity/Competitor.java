package com.hkllyx.demo.basic.enumeration.multipledispatching.entity;

/**
 * @author HKLLY
 * @date 2019/4/8
 */
public interface Competitor<T> {
    /**
     * 对比
     *
     * @param t 需要与之对比的对象
     * @return 对比结果
     */
    Outcome compete(T t);
}
