package com.hkllyx.demo.basic.enumeration;

/**
 * 枚举相关的switch case语句
 *
 * @author HKLLY
 * @date 2019/4/7
 */
public class SwitchCaseDemo {
    private Signal light = Signal.RED;

    public static void main(String[] args) {
        SwitchCaseDemo demo = new SwitchCaseDemo();
        for (int i = 0; i < 10; i++) {
            System.out.println(demo);
            demo.change();
        }
    }

    public void change() {
        switch (light) {
            // 直接写字段, 不允许是用枚举.字段(如: Signal.RED)
            case RED:
                light = Signal.GREEN;
                break;
            case GREEN:
                light = Signal.YELLOW;
                break;
            case YELLOW:
                light = Signal.RED;
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return "Traffic light is " + light;
    }

    /**
     * 信号灯颜色
     *
     * @author HKLLY
     * @date 2019/4/7
     */
    enum Signal {
        /**
         * red light
         */
        RED("red light"),
        /**
         * green light
         */
        GREEN("green light"),
        /**
         * yellow light
         */
        YELLOW("yellow light");

        private final String description;

        /**
         * 初始化字段
         *
         * @param description 字段的描述
         */
        Signal(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return "[" + ordinal() + " : " + name() + "]";
        }
    }
}
