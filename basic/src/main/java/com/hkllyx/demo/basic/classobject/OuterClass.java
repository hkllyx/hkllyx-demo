package com.hkllyx.demo.basic.classobject;

/**
 * @author HKLLY
 * @date 2019-07-09
 */
public class OuterClass {
    private static final String constField = "constField";
    private static String staticField = "staticField";
    private String field = "field";

    public static void staticMethod() {
        StaticInnerClass sic = new StaticInnerClass();
        int i = sic.k;
        i = StaticInnerClass.i;
        sic.m1();
        StaticInnerClass.m2();
    }

    public void publicMethod() {
        //通过this.new方法获取内部类对象
        InnerClass ic = this.new InnerClass();
        //通过new OuterClass.InnerClass()获取内部类对象
        InnerClass ic2 = new OuterClass.InnerClass();
        //所属外部类直接new InnerClass()获取内部类对象
        InnerClass ic3 = new InnerClass();
        //外部类无法直接获取内部类属性和方法
        //int innerField = i;
        //m1();
        //可以通过类获取对象获取内部类所以属性和方法
        int ii = InnerClass.i;
        ii = ic.k;
        ic.m1();

        //不能依赖外部类对象来获取静态内部类对象
        //StaticInnerClass sic = this.new StaticInnerClass();
        //通过new OuterClass.InnerClass()获取内部类对象
        StaticInnerClass sic1 = new OuterClass.StaticInnerClass();
        //所属外部类直接new InnerClass()获取内部类对象
        StaticInnerClass sic2 = new StaticInnerClass();
    }

    private void privateMethod() {
    }

    /**
     * 它的创建是不需要依赖于外围类的。
     * 它不能使用任何外围类的非static成员变量和方法。
     */
    static class StaticInnerClass {
        static final int i = 0;
        static int j = 1;
        int k = 2;

        static void m2() {
            //内部类静态方法可以获取外部类所以静态属性及静态方法
            String s = constField;
            s = staticField;
            //s = field;

            staticMethod();
            //publicMethod();
            //privateMethod();
        }

        void m1() {
            //内部类成员方法可以获取外部类所以静态属性及静态方法
            String s = constField;
            s = staticField;
            //s = field;

            staticMethod();
            //publicMethod();
            //privateMethod();
        }
    }

    /**
     * 成员内部类中不能存在任何static的变量和方法
     * 成员内部类是依附于外围类的，所以只有先创建了外围类才能够创建内部类
     * 非静态内部类在编译完成之后会隐含地保存着一个引用，该引用是指向创建它的外围类
     */
    public class InnerClass {
        private static final int i = 0;
        //成员内部类不能定义静态域
        //static int j = 1;
        private int k = 2;

        void m1() {
            //内部类可以获取外部类所以属性及方法
            String s = constField;
            s = staticField;
            s = field;

            staticMethod();
            publicMethod();
            privateMethod();
        }

        //成员内部类不能定义静态方法
        //static void m2() {}
    }
}
