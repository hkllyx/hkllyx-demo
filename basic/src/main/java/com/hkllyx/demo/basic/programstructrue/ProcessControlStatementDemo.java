package com.hkllyx.demo.basic.programstructrue;

/**
 * 流程控制语句
 *
 * @author HKLLY
 * @date 2019-08-07
 */
public class ProcessControlStatementDemo {
    public static void main(String[] args) {
        System.out.print("测试 continue: ");
        f1();
        System.out.print("测试 break: ");
        f2();
        System.out.print("测试 return: ");
        f3();
        System.out.println("\n测试 label: ");
        f4();
    }

    static void f1() {
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                continue;
            }
            System.out.print(i + "  ");
        }
        System.out.println("f1() finished");
    }

    static void f2() {
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                break;
            }
            System.out.print(i + "  ");
        }
        System.out.println("f2() finished");
    }

    static void f3() {
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                return;
            }
            System.out.print(i + "  ");
        }
        System.out.println("f3() finished");
    }

    static void f4() {
        int i = 0;
        outer:
        while (true) {
            System.out.println("外层循环输出 " + i);
            while (true) {
                i++;
                if (i == 2) {
                    System.out.println("    continue内层 " + i);
                    continue;
                }
                if (i == 4) {
                    System.out.println("    continue跳出至外层 " + i);
                    continue outer;
                }
                if (i == 6) {
                    System.out.println("    break内层 " + i);
                    break;
                }
                if (i == 8) {
                    System.out.println("    break跳出至外层 " + i);
                    break outer;
                }
                System.out.println("    i = " + i);
            }
        }
    }
}
