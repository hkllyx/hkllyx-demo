package com.hkllyx.demo.basic.reflect.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * 显示一个类、接口的信息
 *
 * @author HKLLY
 * @date 2019-08-12
 */
public class TypeInfoPrinter {

    public static void main(String[] args) throws ClassNotFoundException {
        String className = "java.util.ArrayList";
        TypeInfoPrinter.printAll(className);
    }

    public static void printAll(Class<?> clazz) {
        System.out.print(getBasicInfo(clazz));
        System.out.println(" {\n");
        for (String field : getFields(clazz)) {
            System.out.print("    ");
            System.out.println(field);
        }
        System.out.println();
        for (String constructor : getConstructors(clazz)) {
            System.out.print("    ");
            System.out.println(constructor);
        }
        System.out.println();
        for (String method : getMethods(clazz)) {
            System.out.print("    ");
            System.out.println(method);
        }
        System.out.println();
        for (String inner : getClasses(clazz)) {
            System.out.print("    ");
            System.out.println(inner);
        }
        System.out.println("}");
    }

    public static void printAll(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        printAll(clazz);
    }

    private static String getBasicInfo(Class<?> clazz) {
        StringBuilder info = new StringBuilder(clazz.toGenericString());
        Type superClass = clazz.getGenericSuperclass();
        if (superClass != null) {
            info.append("\n    extends ").append
                    (superClass.toString().replace("class ", ""));
        }
        Type[] ints = clazz.getGenericInterfaces();
        if (ints != null && ints.length != 0) {
            info.append("\n    implements ")
                    .append(ints[0].toString().replace("interface ", ""));
            int i = 1;
            while (i < ints.length) {
                info.append(",\n               ")
                        .append(ints[i++].toString().replace("interface ", ""));
            }
        }
        return info.toString();
    }

    public static void printFields(Class<?> clazz) {
        for (String field : getFields(clazz)) {
            System.out.println(field);
        }
    }

    public static void printFields(String className) throws ClassNotFoundException {
        printFields(Class.forName(className));
    }

    private static String[] getFields(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        String[] strs = new String[fields.length];
        String rep = clazz.getCanonicalName() + ".";
        for (int i = 0; i < strs.length; i++) {
            strs[i] = fields[i].toGenericString().replace(rep, "");
        }
        return strs;
    }

    public static void printConstructors(Class<?> clazz) {
        for (String constructor : getConstructors(clazz)) {
            System.out.println(constructor);
        }
    }

    public static void printConstructors(String className) throws ClassNotFoundException {
        printConstructors(Class.forName(className));
    }

    private static String[] getConstructors(Class<?> clazz) {
        Constructor<?>[] cons = clazz.getDeclaredConstructors();
        String[] strs = new String[cons.length];
        String canonicalName = clazz.getCanonicalName();
        String simpleName = clazz.getSimpleName();
        for (int i = 0; i < strs.length; i++) {
            strs[i] = cons[i].toGenericString().replace(canonicalName, simpleName);
        }
        return strs;
    }

    public static void printMethods(Class<?> clazz) {
        for (String method : getMethods(clazz)) {
            System.out.println(method);
        }
    }

    public static void printMethods(String className) throws ClassNotFoundException {
        printMethods(Class.forName(className));
    }

    private static String[] getMethods(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        String[] strs = new String[methods.length];
        String rep = clazz.getCanonicalName() + ".";
        for (int i = 0; i < strs.length; i++) {
            strs[i] = methods[i].toGenericString().replace(rep, "");
        }
        return strs;
    }

    public static void printClasses(Class<?> clazz) {
        for (String inner : getClasses(clazz)) {
            System.out.println(inner);
        }
    }

    public static void printClasses(String className) throws ClassNotFoundException {
        printClasses(Class.forName(className));
    }

    private static String[] getClasses(Class<?> clazz) {
        Class<?>[] inners = clazz.getDeclaredClasses();
        String[] strs = new String[inners.length];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = inners[i].toGenericString();
        }
        return strs;
    }
}
