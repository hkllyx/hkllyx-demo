package com.hkllyx.demo.basic.enumeration.chainofresponsibility;

import com.hkllyx.demo.basic.enumeration.EnumUtils;

import java.util.Iterator;

/**
 * 模拟邮件
 *
 * @author HKLLY
 * @date 2019/4/8
 */
public class Mail {
    /**
     * 是否正常被递送, YES为正常, NO有多个,提高值为NO的可能
     */
    enum GeneralDelivery {YES, NO1, NO2, NO3, NO4, NO5}

    /**
     * 是否可以扫描, UNSCANNABLE表示不可以扫描, YES有多个, 提高值为YES的可能
     */
    enum Scannability {UNSCANNABLE, YES1, YES2, YES3, YES4}

    /**
     * 是否可读, ILLEGIBLE表示模糊不可读, YES有多个, 提高值为YES的可能
     */
    enum Readability {ILLEGIBLE, YES1, YES2, YES3, YES4}

    /**
     * 地址是否正确, INCORRECT为不正确, OK有多个, 提高值为OK的可能
     */
    enum Address {INCORRECT, OK1, OK2, OK3, OK4, OK5, OK6}

    /**
     * 是否丢失退回地址, MISSING为丢失,OK有多个, 提高值为OK的可能
     */
    enum ReturnAddress {MISSING, OK1, OK2, OK3, OK4, OK5}

    /**
     * 邮件的标识
     */
    private static long counter = 0;
    private final long id = counter++;
    /**
     * 是否正常被递送
     */
    GeneralDelivery generalDelivery;
    /**
     * 是否可以扫描
     */
    Scannability scannability;
    /**
     * 是否可读
     */
    Readability readability;
    /**
     * 地址是否正确
     */
    Address address;
    /**
     * 是否丢失退回地址
     */
    ReturnAddress returnAddress;

    /**
     * 获取邮件基本信息
     *
     * @return 邮件基本信息
     */
    @Override
    public String toString() {
        return "Mail " + id;
    }

    /**
     * 获取邮件详细信息
     *
     * @return 邮件详细信息
     */
    public String details() {
        return toString() +
                ",\n General Delivery: " + generalDelivery +
                ",\n Address Scanability: " + scannability +
                ",\n Address Readability: " + readability +
                ",\n Address Address: " + address +
                ",\n Return address: " + returnAddress;
    }

    /**
     * 生成一个各种状态随机的邮件
     *
     * @return 各种状态随机的邮件
     */
    public static Mail randomMail() {
        Mail m = new Mail();
        m.generalDelivery = EnumUtils.random(GeneralDelivery.class);
        m.scannability = EnumUtils.random(Scannability.class);
        m.readability = EnumUtils.random(Readability.class);
        m.address = EnumUtils.random(Address.class);
        m.returnAddress = EnumUtils.random(ReturnAddress.class);
        return m;
    }

    /**
     * 生成指定数目的邮件,并保存在一个Iterable实例中
     *
     * @param count 需要生成邮件的数目
     * @return 包含指定数目邮件的Iterable实例
     */
    public static Iterable<Mail> mailGenerator(final int count) {
        return new Iterable<Mail>() {
            int n = count;

            @Override
            public Iterator<Mail> iterator() {
                return new Iterator<Mail>() {
                    @Override
                    public boolean hasNext() {
                        return n-- > 0;
                    }

                    @Override
                    public Mail next() {
                        return randomMail();
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}
