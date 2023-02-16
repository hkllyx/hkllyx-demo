package com.hkllyx.demo.basic.concurrent.others.simulation.restaurant;

import com.hkllyx.demo.basic.enumeration.EnumUtils;

/**
 * 饭餐
 *
 * @author HKLLY
 * @date 2019/4/14
 */
public enum Course {
    /**
     * 开胃菜
     */
    APPETIZER(Food.Appetizer.class),
    /**
     * 主餐
     */
    MAINCOURSE(Food.MainCourse.class),
    /**
     * 甜点
     */
    DESSERT(Food.Dessert.class),
    /**
     * 咖啡
     */
    COFFEE(Food.Coffee.class);

    private Food[] values;

    Course(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }

    public Food randomSelection() {
        return EnumUtils.random(values);
    }
}
