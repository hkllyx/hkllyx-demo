package com.hkllyx.demo.basic.generic.generator;

import java.util.Random;

/**
 * 随机生成基本类型值以及String类型对象
 *
 * @author HKLLY
 * @date 2019/4/3
 */
public class RandomGenerator {
    /**
     * 随机生成器对象
     */
    private static final Random RANDOM = new Random(47);

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
    private final Byte byteGenerator;
    private final Short shortGenerator;
    private final Integer integerGenerator;
    private final Long longGenerator;
    private final String stringGenerator;
    private final Character characterGenerator = new Character();
    private final Float floatGenerator = new Float();
    private final Double doubleGenerator = new Double();
    private final Boolean booleanGenerator = new Boolean();

    public RandomGenerator() {
        byteGenerator = new Byte();
        shortGenerator = new Short();
        integerGenerator = new Integer();
        longGenerator = new Long();
        stringGenerator = new String();
    }

    public RandomGenerator(byte byteBound,
            short shortBound,
            int intBound,
            int longBound,
            int stringLength) {
        byteGenerator = new Byte(byteBound);
        shortGenerator = new Short(shortBound);
        integerGenerator = new Integer(intBound);
        longGenerator = new Long(longBound);
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
    public static class Byte implements Generator<java.lang.Byte> {
        byte bound = java.lang.Byte.MAX_VALUE;

        public Byte() {
        }

        public Byte(byte bound) {
            this.bound = bound;
        }

        @Override
        public java.lang.Byte next() {
            return (byte) RANDOM.nextInt(bound);
        }
    }

    /**
     * short生成器
     */
    public static class Short implements Generator<java.lang.Short> {
        short bound = java.lang.Short.MAX_VALUE;

        public Short() {
        }

        public Short(short bound) {
            this.bound = bound;
        }

        @Override
        public java.lang.Short next() {
            return (short) RANDOM.nextInt(bound);
        }
    }

    /**
     * int生成器
     */
    public static class Integer implements Generator<java.lang.Integer> {
        int bound = java.lang.Integer.MAX_VALUE;

        public Integer() {
        }

        public Integer(int bound) {
            this.bound = bound;
        }

        @Override
        public java.lang.Integer next() {
            return RANDOM.nextInt();
        }
    }

    /**
     * long生成器
     */
    public static class Long implements Generator<java.lang.Long> {
        long bound;

        public Long() {
        }

        public Long(long bound) {
            this.bound = bound;
        }

        @Override
        public java.lang.Long next() {
            return RANDOM.nextLong();
        }
    }

    /**
     * char生成器
     */
    public static class Character implements Generator<java.lang.Character> {
        @Override
        public java.lang.Character next() {
            return LETTERS[RANDOM.nextInt(LETTERS.length)];
        }
    }

    /**
     * float生成器
     */
    public static class Float implements Generator<java.lang.Float> {
        float bound = java.lang.Float.MAX_VALUE;

        public Float() {
        }

        @Override
        public java.lang.Float next() {
            float result = RANDOM.nextFloat();
            while (result > bound) {
                result = RANDOM.nextFloat();
            }
            return result;
        }
    }

    /**
     * double生成器
     */
    public static class Double implements Generator<java.lang.Double> {
        double bound = java.lang.Double.MAX_VALUE;

        public Double() {
        }

        @Override
        public java.lang.Double next() {
            double result = RANDOM.nextDouble();
            while (result > bound) {
                result = RANDOM.nextDouble();
            }
            return result;
        }
    }

    /**
     * boolean生成器
     */
    public static class Boolean implements Generator<java.lang.Boolean> {
        @Override
        public java.lang.Boolean next() {
            return RANDOM.nextBoolean();
        }
    }

    /**
     * String生成器
     */
    public static class String implements Generator<java.lang.String> {
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
