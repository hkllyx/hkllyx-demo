package com.hkllyx.demo.basic.generic.generator;

/**
 * 基本生成器
 *
 * @author HKLLY
 * @date 2019/4/14
 */
public class BasicGenerator<T> implements Generator<T> {
    private final Class<T> type;

    public BasicGenerator(Class<T> type) {
        this.type = type;
    }

    /**
     * 生成一个 T 类型对象
     *
     * @return T 类型对象
     */
    @Override
    public T next() {
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Generator failed!");
        }
    }

    /**
     * 创建一个生成器
     *
     * @param type 生成器的类对象
     * @param <T>  生成器的类型
     * @return T型生成器
     */
    public static <T> Generator<T> create(Class<T> type) {
        return new BasicGenerator<>(type);
    }
}
