package com.hkllyx.demo.basic.reflect;

/**
 * @author xiaoyong3
 * @date 2022/05/21
 */
class IntHolder {
    private Integer i;

    private void setInt(Integer i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "i=" + i +
                '}';
    }
}
