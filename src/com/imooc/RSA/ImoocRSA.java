package com.imooc.RSA;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

/**
 * Created by anyuan on 2016/11/23.
 */
public class ImoocRSA {
    public static final String src = "imooc security rsa";

    public static void main(String[] args) throws Exception {
        jdkRSA();
    }

    public static void jdkRSA() throws Exception {
        //初始化密钥
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);//512~65532
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //私钥加密，公钥解密
        //下面是产生密钥工厂，用公钥或者私钥产生密钥工厂，进而产生密钥。原则上这里可以省略.密钥转换也可以省略
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        PrivateKey privateKey = keyFactory.generatePrivate((KeySpec) keyPair.getPrivate());
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPrivate());
        byte[] result = cipher.doFinal(src.getBytes());
        System.out.println("**私钥加密，公钥解密**");
        System.out.println("加密：" + Hex.encodeHexString(result));

        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPublic());
        result = cipher.doFinal(result);
        System.out.println("解密：" + new String(result));
    }

}
