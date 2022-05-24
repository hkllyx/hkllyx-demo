package com.hkllyx.demo.basic.enumeration;

/**
 * @author xiaoyong3
 * @date 2022/05/20
 */
public class SimpleEnumDemo {

    public static void main(String[] args) {
        // 每个枚举常量默认有一个属性：name，值就是常量名，默认toString()方法也是返回name
        System.out.println(Day.valueOf("MONDAY"));

        // 也可以定义一个字段，每个枚举常量都包含该字段
        System.out.println(Day2.valueOf("MONDAY").getDesc());
    }

    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
    }

    enum Day2 {
        MONDAY("星期一"),
        TUESDAY("星期二"),
        WEDNESDAY("星期三"),
        THURSDAY("星期四"),
        FRIDAY("星期五"),
        SATURDAY("星期六"),
        SUNDAY("星期日");

        private final String desc;

        Day2(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }
}
