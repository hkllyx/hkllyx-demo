package com.hkllyx.demo.basic.generic.generator;

/**
 * @author HKLLY
 * @date 2019-07-18
 */
public class GeneratorDemo {

    public static void main(String[] args) {
        CountingGenerator cg = new CountingGenerator();
        RandomGenerator rg = new RandomGenerator();
        for (int i = 0; i < 10; i++) {
            System.out.print(cg.nextByte() + " | ");
            System.out.println(rg.nextByte());
            System.out.print(cg.nextShort() + " | ");
            System.out.println(rg.nextShort());
            System.out.print(cg.nextInt() + " | ");
            System.out.println(rg.nextInt());
            System.out.print(cg.nextChar() + " | ");
            System.out.println(rg.nextChar());
            System.out.print(cg.nextLong() + " | ");
            System.out.println(rg.nextLong());
            System.out.print(cg.nextFloat() + " | ");
            System.out.println(rg.nextFloat());
            System.out.print(cg.nextDouble() + " | ");
            System.out.println(rg.nextDouble());
            System.out.print(cg.nextBoolean() + " | ");
            System.out.println(rg.nextBoolean());
            System.out.print(cg.nextString() + " | ");
            System.out.println(rg.nextString());
            System.out.println("=========================");
        }
    }
}
