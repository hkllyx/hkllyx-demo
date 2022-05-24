package com.hkllyx.demo.basic.generic.generator;

/**
 * 生成器
 *
 * @author HKLLY
 * @date 2019-07-10
 */
public interface Generator<T> {

    /**
     * 生成一个T类型对象
     *
     * @return T类型对象
     */
    T next();
}
