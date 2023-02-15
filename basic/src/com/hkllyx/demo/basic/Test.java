package com.hkllyx.demo.basic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xiaoyong3
 * @date 2023/02/15
 */
public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("zhangsan");
        list.add("simon");
        list.add("lisi");
        list.add("smith");
        list.add("lucy");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals("lisi")) {
                iterator.remove();
            }
        }

        System.out.println(list);

    }
}
