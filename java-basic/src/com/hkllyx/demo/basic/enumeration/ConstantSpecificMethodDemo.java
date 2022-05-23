package com.hkllyx.demo.basic.enumeration;

import java.text.DateFormat;
import java.util.Date;

/**
 * 常量相关方法：<br>
 * enum中定义一个抽象方法，每个枚举值都必须实现这个方法
 *
 * @author HKLLY
 * @date 2019/4/8
 */
public class ConstantSpecificMethodDemo {

    public static void main(String[] args) {
        for (Property property : Property.values()) {
            System.out.println(property.getInfo());
        }
    }

    enum Property {
        /** 日期时间 */
        DATATIME {
            @Override
            String getInfo() {
                return DateFormat.getDateInstance().format(new Date());
            }
        },

        /** 环境变量中的classpath */
        CLASSPATH {
            @Override
            String getInfo() {
                return System.getenv("CLASSPATH");
            }
        },

        /** 环境变量中的version */
        VERSION {
            @Override
            String getInfo() {
                return System.getenv("VERSION");
            }
        };

        /** 每一个枚举常量都需要实现的方法 */
        abstract String getInfo();
    }
}
