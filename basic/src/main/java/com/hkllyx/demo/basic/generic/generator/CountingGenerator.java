package com.hkllyx.demo.basic.generic.generator;

/**
 * 递增生成基本类型值以及String对象
 *
 * @author HKLLY
 * @date 2019/4/3
 */
public class CountingGenerator {
    /**
     * 英文字母表
     */
    private static final char[] LETTERS = new char[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z'
    };

    /**
     * 生成器实例对象
     */
    private final Byte byteGenerator = new Byte();
    private final Short shortGenerator = new Short();
    private final Integer integerGenerator = new Integer();
    private final Long longGenerator = new Long();
    private final Character characterGenerator = new Character();
    private final Float floatGenerator = new Float();
    private final Double doubleGenerator = new Double();
    private final Boolean booleanGenerator = new Boolean();
    private final String stringGenerator;

    public CountingGenerator() {
        stringGenerator = new String();
    }

    public CountingGenerator(int stringLength) {
        stringGenerator = new String(stringLength);
    }

    /**
     * 生成byte
     *
     * @return byte
     */
    public byte nextByte() {
        return byteGenerator.next();
    }

    /**
     * 生成short
     *
     * @return short
     */
    public short nextShort() {
        return shortGenerator.next();
    }

    /**
     * 生成int
     *
     * @return int
     */
    public int nextInt() {
        return integerGenerator.next();
    }

    /**
     * 生成long
     *
     * @return long
     */
    public long nextLong() {
        return longGenerator.next();
    }

    /**
     * 生成char
     *
     * @return char
     */
    public char nextChar() {
        return characterGenerator.next();
    }

    /**
     * 生成float
     *
     * @return float
     */
    public float nextFloat() {
        return floatGenerator.next();
    }

    /**
     * 生成double
     *
     * @return double
     */
    public double nextDouble() {
        return doubleGenerator.next();
    }

    /**
     * 生成boolean
     *
     * @return boolean
     */
    public boolean nextBoolean() {
        return booleanGenerator.next();
    }

    /**
     * 生成String
     *
     * @return String
     */
    public java.lang.String nextString() {
        return stringGenerator.next();
    }

    /**
     * byte生成器
     */
    private static class Byte implements Generator<java.lang.Byte> {
        byte aByte = 0;

        @Override
        public java.lang.Byte next() {
            return aByte++;
        }
    }

    /**
     * short生成器
     */
    private static class Short implements Generator<java.lang.Short> {
        short aShort = 0;

        @Override
        public java.lang.Short next() {
            return aShort++;
        }
    }

    /**
     * int生成器
     */
    private static class Integer implements Generator<java.lang.Integer> {
        int anInt = 0;

        @Override
        public java.lang.Integer next() {
            return anInt++;
        }
    }

    /**
     * long生成器
     */
    private static class Long implements Generator<java.lang.Long> {
        long aLong = 0;

        @Override
        public java.lang.Long next() {
            return aLong++;
        }
    }

    /**
     * 英文字母char生成器
     */
    private static class Character implements Generator<java.lang.Character> {
        int index = 0;

        @Override
        public java.lang.Character next() {
            return LETTERS[(index++) % LETTERS.length];
        }
    }

    /**
     * float生成器
     */
    private static class Float implements Generator<java.lang.Float> {
        float aFloat = 0;

        @Override
        public java.lang.Float next() {
            return aFloat++;
        }
    }

    /**
     * double生成器
     */
    private static class Double implements Generator<java.lang.Double> {
        double aDouble = 0;

        @Override
        public java.lang.Double next() {
            return aDouble++;
        }
    }

    /**
     * boolean生成器
     */
    private static class Boolean implements Generator<java.lang.Boolean> {
        boolean aBoolean = false;

        @Override
        public java.lang.Boolean next() {
            aBoolean = !aBoolean;
            return aBoolean;
        }
    }

    /**
     * 英文字母String生成器
     */
    private static class String implements Generator<java.lang.String> {
        int length = 7;
        Generator<java.lang.Character> gc = new Character();

        public String() {
        }

        public String(int length) {
            this.length = length;
        }

        @Override
        public java.lang.String next() {
            char[] chars = new char[length];
            for (int i = 0; i < length; i++) {
                chars[i] = gc.next();
            }
            return new java.lang.String(chars);
        }
    }
}
