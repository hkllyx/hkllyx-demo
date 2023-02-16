package com.hkllyx.demo.algorithm.recursion;

/**
 * @author xiaoyong3
 * @date 2023/02/16
 */
public class Fibonacci {

    public int fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    public static void main(String[] args) {
        int n = 10;
        Fibonacci fibonacci = new Fibonacci();
        for (int i = 1; i <= n; i++) {
            System.out.println(fibonacci.fib(i));
        }
    }
}
