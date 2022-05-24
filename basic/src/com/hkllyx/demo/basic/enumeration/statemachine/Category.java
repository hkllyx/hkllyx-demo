package com.hkllyx.demo.basic.enumeration.statemachine;

import java.util.EnumMap;

/**
 * 操作类别
 *
 * @author HKLLY
 * @date 2019/4/8
 */
public enum Category {
    /**
     * 金额
     */
    MONEY(Input.NICKEL, Input.DIME, Input.QUARTER, Input.DOLLAR),
    /**
     * 选择的商品
     */
    ITEM_SELECTION(Input.TOOTHPASTE, Input.CHIPS, Input.SODA, Input.SOAP),
    /**
     * 放弃事务选项
     */
    QUIT_TRANSACTION(Input.ABORT_TRANSACTION),
    /**
     * 停止选项
     */
    SHUT_DOWN(Input.STOP);

    /**
     * input映射到category的map
     */
    private static EnumMap<Input, Category> categories =
            new EnumMap<>(Input.class);

    // 初始化map
    static {
        for (Category c : Category.class.getEnumConstants()) {
            for (Input input : c.values) {
                categories.put(input, c);
            }
        }
    }

    /**
     * 保存category对应的input
     */
    private Input[] values;

    /**
     * 初始化分类
     *
     * @param inputs 输入
     */
    Category(Input... inputs) {
        values = inputs;
    }

    /**
     * 根据输入获取其分类
     *
     * @param input 输入
     * @return 对应分类
     */
    public static Category categorize(Input input) {
        return categories.get(input);
    }
}
