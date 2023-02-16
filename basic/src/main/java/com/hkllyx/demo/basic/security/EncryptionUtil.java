package com.hkllyx.demo.basic.security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.*;

/**
 * MD5, BASE64, DES, RSA 加密界面
 *
 * @author HKLLY
 * @date 2019-06-13
 */
public class EncryptionUtil {
    private static final String MD5_ALGORITHM = "md5";
    private static final String DES_ALGORITHM = "des";
    private static final int RSA_KEY_SIZE = 1024;
    private static final String RSA_ALGORITHM = "rsa";
    private static final String SIGN_ALGORITHM = "MD5withRSA";
    
    private final static MessageDigest MD5 = initMD5();
    private final static BASE64Encoder BASE64_ENCODER = new BASE64Encoder();
    private final static BASE64Decoder BASE64_DECODER = new BASE64Decoder();
    private final static KeyPair RSA_KEY_PAIR = createRsaKeyPair();

    private static MessageDigest initMD5() {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(MD5_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return messageDigest;
    }
    
    /**
     * 消息摘要算法第五版(Message Digest Algorithm)，是一种单向加密算法，只能加密、无法解密。
     * 然而 MD5 加密算法已经被中国山东大学王小云教授成功破译，但是在安全性要求不高的场景下，
     * MD5 加密算法仍然具有应用价值。
     *
     * @param plainText 明文
     * @return 密文
     */
    public static String encryptMd5(String plainText) {
        byte[] cipherData = MD5.digest(plainText.getBytes());
        //将摘要转换成16进制字符串
        StringBuilder sb = new StringBuilder();
        for (byte cipher : cipherData) {
            String toHexStr = Integer.toHexString(cipher & 0xff);
            sb.append(toHexStr.length() == 1 ? "0" + toHexStr : toHexStr);
        }
        return sb.toString();
    }
    
    /**
     * BASE64算法通常用作对二进制数据进行加密，加密之后的数据不易被肉眼识别。严格来说，
     * 经过BASE64加密的数据其实没有安全性可言保密，因为它的加密解密算法都是公开的。
     * 经过标准的BASE64算法加密后的数据，通常包含/、+、=等特殊符号，不适合作为url参数传递，
     * 幸运的是Apache的Commons Codec模块提供了对BASE64的进一步封装。
     *
     * @param plainText 明文
     * @return 密文
     */
    public static String encryptBase64(String plainText) {
        return BASE64_ENCODER.encode(plainText.getBytes());
    }
    
    /**
     * BASE64解密
     *
     * @param cipherText 密文
     * @return 明文
     * @throws IOException IOException
     */
    public static String decryptBase64(String cipherText) throws IOException {
        return new String(BASE64_DECODER.decodeBuffer(cipherText));
    }
    
    /**
     * 数据加密标准算法(Data com.hkllyx.encrypt.EncryptionUtil Standard)，和BASE64最明显的区别就是有一个工作密钥，
     * 该密钥既用于加密、也用于解密，并且要求密钥是一个长度至少大于8位的字符串。
     * 使用DES加密、解密的核心是确保工作密钥的安全性。
     *
     * @param plainText 明文
     * @param key       秘钥
     * @return 密文
     */
    public static String encryptDes(String plainText, String key) {
        //获取加密前数据
        byte[] plainData = plainText.getBytes();
        //获取加密数据
        byte[] cipherData = processCipher
                (plainData, DES_ALGORITHM, Cipher.ENCRYPT_MODE, createDesKey(key));
        //再使用BASE64加密
        return BASE64_ENCODER.encode(cipherData);
    }
    
    /**
     * DES解密
     *
     * @param cipherText 密文
     * @param key        秘钥
     * @return 明文
     * @throws IOException IOException
     */
    public static String decryptDes(String cipherText, String key) throws IOException {
        //获取加密数据
        byte[] cipherData = BASE64_DECODER.decodeBuffer(cipherText);
        //获取解密数据
        byte[] plainData = processCipher
                (cipherData, DES_ALGORITHM, Cipher.DECRYPT_MODE, createDesKey(key));
        return new String(plainData);
    }
    
    /**
     * RSA算法是非对称加密算法的典型代表，既能加密、又能解密。和对称加密算法比如DES的明显区别在于用于加密、
     * 解密的密钥是不同的。使用RSA算法，只要密钥足够长(一般要求1024bit)，加密的信息是不能被破解的。
     *
     * @param plainText 明文
     * @param key       私钥
     * @return 明文
     */
    public static String encryptRsa(String plainText, PrivateKey key) {
        //获取加密前数据
        byte[] plainData = plainText.getBytes();
        //获取加密数据
        byte[] cipherData = processCipher(plainData, RSA_ALGORITHM, Cipher.ENCRYPT_MODE, key);
        //再使用BASE64加密
        return new BASE64Encoder().encode(cipherData);
    }
    
    /**
     * RSA解密
     *
     * @param cipherText 密文
     * @param key        公钥
     * @return 明文
     * @throws IOException IOException
     */
    public static String decryptRsa(String cipherText, PublicKey key) throws IOException {
        // 获取加密数据
        byte[] cipherData = BASE64_DECODER.decodeBuffer(cipherText);
        // 获取解密数据
        byte[] plainData = processCipher(cipherData, RSA_ALGORITHM, Cipher.DECRYPT_MODE, key);
        // 解密
        return new String(plainData);
    }
    
    /**
     * 创建 RSA 签名数据
     *
     * @param plainText 明文
     * @param key       秘钥
     * @return 签名数据
     * @throws Exception Exception
     */
    public static byte[] createRsaSignData(String plainText, PrivateKey key) throws Exception {
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        //初始化签名
        signature.initSign(key);
        //更新签名数据
        byte[] cipherData = processCipher(plainText.getBytes(), RSA_ALGORITHM, Cipher.ENCRYPT_MODE, key);
        signature.update(cipherData);
        //签名
        return signature.sign();
    }
    
    /**
     * 验证签名数据
     *
     * @param cipherText 密文
     * @param key        公钥
     * @param signData   签名数据
     * @return 验证结果
     * @throws Exception Exception
     */
    public static boolean verifyRsaSignData(String cipherText,
                                            PublicKey key,
                                            byte[] signData) throws Exception {
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        //初始化验证
        signature.initVerify(key);
        //更新验证数据
        byte[] cipherData = BASE64_DECODER.decodeBuffer(cipherText);
        signature.update(cipherData);
        //验证
        return signature.verify(signData);
    }
    
    /**
     * 获取 DES 秘钥
     *
     * @param key 秘钥
     * @return 转换后的秘钥
     */
    public static SecretKey createDesKey(String key) {
        try {
            //获取 DESKeySpec
            DESKeySpec keySpec = new DESKeySpec(key.getBytes());
            //获取 SecretKey（工厂模式）
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);
            return secretKeyFactory.generateSecret(keySpec);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
    /**
     * 获取 RSA 公钥和私钥
     *
     * @return RSA 公钥和私钥
     */
    public static KeyPair createRsaKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
            keyPairGenerator.initialize(RSA_KEY_SIZE);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }
    
    /**
     * 使用 Cipher 获取加密或解密数据
     *
     * @param data      明文或密文数据
     * @param algorithm 加密算法
     * @param mode      加密或解密模式
     * @param key       密匙
     * @return 加密或解密数据
     */
    private static byte[] processCipher(byte[] data,
                                        String algorithm,
                                        int mode,
                                        Key key) {
        try {
            //获取 Cipher 实例
            Cipher cipher = Cipher.getInstance(algorithm);
            //初始化 Cipher
            cipher.init(mode, key, new SecureRandom());
            //获取 CipherData
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
    private EncryptionUtil() {}
}
