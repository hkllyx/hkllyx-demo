package com.hkllyx.demo.basic.programstructrue;

import java.util.Arrays;

/**
 * 操作符 +（加）、&（按位与）
 *
 * @author HKLLY
 * @date 2019-08-06
 */
public class OperatorDemo {
    public static void main(String[] args) {
        int i0 = 1;
        int i1 = 2;
        String s = "3";
        System.out.println("i0 + i1 + s = " + i0 + i1 + s);
        System.out.println("s + i0 + i1 = " + s + i0 + i1);
        System.out.println("i0 + s + i1 = " + i0 + s + i1);

        boolean b0 = true;
        boolean b1 = false;
        System.out.println("b0 & b1 = " + (b0 & b1));
        System.out.println("b0 | b1 = " + (b0 | b1));
        System.out.println("b0 ^ b1 = " + (b0 ^ b1));

        int[][] d1 = new int[2][];
        d1[0] = new int[1];
        d1[1] = new int[2];
        int[][] d2 = new int[2][2];
        int[][] d3 = new int[][]{{1, 2}, {3, 4}};
        int[][] d4 = {{1, 2}, {3, 4}};
        System.out.println(Arrays.deepToString(d1));
    }
}
