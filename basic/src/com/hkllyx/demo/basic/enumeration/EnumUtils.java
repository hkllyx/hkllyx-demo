package com.hkllyx.demo.basic.enumeration;

import java.util.Random;

/**
 * 随机生成枚举字段
 *
 * @author HKLLY
 * @date 2019/4/8
 */
public class EnumUtils {
    private static final Random RANDOM = new Random(47);

    private EnumUtils() {
    }

    /**
     * 从一个枚举所有字段中选取一个字段
     *
     * @param ec  枚举类的类对象
     * @param <T> 枚举类(所有枚举类都继承了Enum类的类)
     * @return 枚举类中的任一个字段
     */
    public static <T extends Enum<T>> T random(Class<T> ec) {
        //  Class类的方法，获取枚举类的values
        return random(ec.getEnumConstants());
    }

    /**
     * 在字段集合随机选取一个字段
     *
     * @param values 字段集合
     * @param <T>    字段对应的枚举
     * @return 字段中任一个
     */
    public static <T> T random(T[] values) {
        return values[RANDOM.nextInt(values.length)];
    }
}
