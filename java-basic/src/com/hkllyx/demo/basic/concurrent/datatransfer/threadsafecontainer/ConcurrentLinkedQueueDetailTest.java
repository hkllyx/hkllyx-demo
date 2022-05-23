package com.hkllyx.demo.basic.concurrent.datatransfer.threadsafecontainer;

/**
 * @author HKLLY
 * @date 2019-08-24
 */
public class ConcurrentLinkedQueueDetailTest {
    public static void main(String[] args) {
        String tail;
        String t = (tail = "com/hkllyx/learning/java/web/old");
        System.out.println("1. tail = " + tail + ", t = " + t);

        tail = "new";
        System.out.println("2. tail = " + tail + ", t = " + t);

        /*
         * 反编译后 t != (t = tail) 部分的代码        栈内常量：栈顶 <-- 栈底
         * aload_2    将 t 加载到栈顶                       "old"
         * aload_1    将 tail 加载到栈顶                    "new", "old"
         * dup        将栈顶元素复制且放到栈顶                "new", "new", "old"
         * astore_2   将栈顶元素存到 t                      "new", "old"
         * if_acmpeq  比较栈顶部两个元素是否相等
         *
         * 综上，isEqual = t != (t = tail)可以拆成
         *     isEqual = t == tail;
         *     t = tail;
         * 先比较后赋值
         */
        boolean isEqual = t != (t = tail);
        System.out.println(isEqual);

        System.out.println("3. tail = " + tail + ", t = " + t);
    }
}
