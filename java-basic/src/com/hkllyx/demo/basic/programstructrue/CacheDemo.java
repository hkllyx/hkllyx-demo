package com.hkllyx.demo.basic.programstructrue;

/**
 * 基本类型与包装类的自动装箱、拆箱
 * 包装类的缓存池
 *
 * @author HKLLY
 * @date 2019-08-06
 */
public class CacheDemo {
    public static void main(String[] args) {
        int i = 40;
        int i0 = 40;
        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);
        Double d1 = 1.0;
        Double d2 = 1.0;

        // true。基本类型比较值
        System.out.println("i=i0: " + (i == i0));
        // true。自动装箱比较缓存池中对象
        System.out.println("i1=i2: " + (i1 == i2));
        // true。运算时自动拆箱转为基本类型，比较值。
        System.out.println("i1=i2+i3: " + (i1 == i2 + i3));
        // false。new 出来的对象 == 比较的是内存地址。
        System.out.println("i4=i5: " + (i4 == i5));
        // true。运算时自动拆箱转为基本类型，比较值。
        System.out.println("i4=i5+i6: " + (i4 == i5 + i6));
        // false。Float、Double 类型不存在缓存池，都是不同的对象
        System.out.println("d1=d2: " + (d1 == d2));

        char c = 1;
        byte s = 65;
        s += 1;
        s++;
        System.out.println(s);
    }
}
