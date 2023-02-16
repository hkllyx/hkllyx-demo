package com.hkllyx.demo.basic.enumeration;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author hklly
 * @date 2020-07-29
 */
public class EnumMapDemo {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Map<SimpleEnumDemo.Day, String> map = new EnumMap<>(SimpleEnumDemo.Day.class);
        Class<?> c = EnumMap.class;
        Field f = c.getDeclaredField("keyUniverse");
        f.setAccessible(true);
        //  会将enum的常量放到名为keyUniverse的数组中
        SimpleEnumDemo.Day[] days = (SimpleEnumDemo.Day[]) f.get(map);
        System.out.println(Arrays.toString(days));

        //  键值对size仍为 0
        System.out.println(map.size());
        System.out.println(map.keySet().size());
        map.put(SimpleEnumDemo.Day.MONDAY, "星期一");
        System.out.println(map.get(SimpleEnumDemo.Day.MONDAY));
    }
}
