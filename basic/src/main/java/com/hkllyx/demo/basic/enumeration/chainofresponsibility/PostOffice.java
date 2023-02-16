package com.hkllyx.demo.basic.enumeration.chainofresponsibility;

/**
 * 模拟邮局
 *
 * @author HKLLY
 * @date 2019/4/8
 */
public class PostOffice {

    public static void main(String[] args) {
        for (Mail mail : Mail.mailGenerator(10)) {
            PostOffice.handle(mail);
        }
    }

    /**
     * 按照邮件处理机制内部顺序处理邮件
     *
     * @param m 需要处理的邮件
     */
    public static void handle(Mail m) {
        // 如果邮件处理机制每一个机制都去处理邮件
        for (MailHandler handler : MailHandler.values()) {
            // 一个机制返回true后, 直接退出处理机制, 否则进入下一处理机制
            if (handler.handle(m)) {
                return;
            }
        }
        // 如果处理机制最终返回false, 既处理机制无法处理
        System.out.println(m + " is a dead letter");
    }

    /**
     * 邮件处理机制（顺序执行）
     */
    enum MailHandler {
        /**
         * 正常递送机制
         * 如果邮件被正常递送, 输出相应信息并返回true退出整个处理机制
         * 否则返回true
         */
        GENERAL_DELIVERY {
            @Override
            boolean handle(Mail m) {
                if (m.generalDelivery == Mail.GeneralDelivery.YES) {
                    System.out.println("Using general delivery for " + m);
                    return true;
                }
                return false;
            }
        },
        /**
         * 邮件扫描机制
         * 如果邮件不扫描或者邮件接收地址不正确, 返回false
         * 否则输出相应信息并返回true
         */
        MACHINE_SCAN {
            @Override
            boolean handle(Mail m) {
                if (m.scannability == Mail.Scannability.UNSCANNABLE) {
                    return false;
                }
                if (m.address == Mail.Address.INCORRECT) {
                    return false;
                }
                System.out.println("Delivering " + m + " automatically");
                return true;
            }
        },
        /**
         * 邮件检查机制
         * 如果邮件不可读或者邮件接收地址不正确, 返回false
         * 否则输出相应信息并返回true
         */
        VISUAL_INSPECTION {
            @Override
            boolean handle(Mail m) {
                if (m.readability == Mail.Readability.ILLEGIBLE) {
                    return false;
                }
                if (m.address == Mail.Address.INCORRECT) {
                    return false;
                }
                System.out.println("Delivering " + m + " normally");
                return true;
            }
        },
        /**
         * 邮件退回机制
         * 如果邮件退回地址不正确, 返回false
         * 否则输出相应信息并返回true
         */
        RETURN_TO_SENDER {
            @Override
            boolean handle(Mail m) {
                if (m.returnAddress == Mail.ReturnAddress.MISSING) {
                    return false;
                }
                System.out.println("Returning " + m + " to sender");
                return true;
            }
        };

        abstract boolean handle(Mail m);
    }
}
