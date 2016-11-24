package com.imooc.RSA;

import org.apache.commons.codec.binary.Hex;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;

/**
 * Created by anyuan on 2016/11/24.
 */
public class ImoocRSA2 {
    public static final String src = "imooc security rsa";

    public static void main(String[] args) throws Exception {
        jdkRSA();

    }

    public static void jdkRSA() throws Exception {
        //初始化密钥
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        //执行签名-密钥
        Signature md5withRSA = Signature.getInstance("MD5withRSA");
        md5withRSA.initSign(keyPair.getPrivate());
        md5withRSA.update(src.getBytes());
        byte[] result = md5withRSA.sign();
        System.out.println("JDK RSA SIGN:" + Hex.encodeHexString(result));

        //验证签名的正确性-公钥
        md5withRSA.initVerify(keyPair.getPublic());
        md5withRSA.update(src.getBytes());//注意这里的update和签名时一样
        boolean verify = md5withRSA.verify(result);
        System.out.println("JDK RSA VERIFY:" + verify);


    }
}
