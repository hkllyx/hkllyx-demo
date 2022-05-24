package com.hkllyx.demo.basic.enumeration.multipledispatching;

import com.hkllyx.demo.basic.enumeration.EnumUtils;
import com.hkllyx.demo.basic.enumeration.multipledispatching.entity.Competitor;

/**
 * 本身无法进行游戏, 为其他游戏提供共同接口
 *
 * @author HKLLY
 * @date 2019/4/8
 */
public class RoShamBoUtil {

    /**
     * a和b比
     *
     * @param a   a
     * @param b   b
     * @param <T> 实现了Competitor接口的类型
     */
    public static <T extends Competitor<T>> void match(T a, T b) {
        System.out.println(a + " vs. " + b + ": " + a.compete(b));
    }

    /**
     * 游戏开始
     *
     * @param rsbClass 包含石头剪刀布字段的枚举的类对象
     * @param size     对比次数
     * @param <T>      包含石头剪刀布字段的枚举的类型
     */
    public static <T extends Enum<T> & Competitor<T>> void play(
            Class<T> rsbClass, int size) {

        // 两个随机生成石头剪刀布石头对比
        for (int i = 0; i < size; i++) {
            match(EnumUtils.random(rsbClass), EnumUtils.random(rsbClass));
        }
    }
}
