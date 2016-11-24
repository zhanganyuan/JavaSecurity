package com.imooc.ECDSA;

import org.apache.commons.codec.binary.Hex;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;

/**
 * Created by anyuan on 2016/11/24.
 */
public class ImoocECDSA {
    public static final String src = "imooc security ecdsa";

    public static void main(String[] args) throws Exception {
        jdkECDSA();
    }

    private static void jdkECDSA() throws Exception {
        KeyPairGenerator ecdsa = KeyPairGenerator.getInstance("EC");//注意这里是EC
        ecdsa.initialize(256);
        KeyPair keyPair = ecdsa.generateKeyPair();

        Signature sha1withECDSA = Signature.getInstance("SHA1withECDSA");
        sha1withECDSA.initSign(keyPair.getPrivate());
        sha1withECDSA.update(src.getBytes());
        byte[] sign = sha1withECDSA.sign();
        System.out.println("JDK ECDSA SIGN:" + Hex.encodeHexString(sign));

        sha1withECDSA.initVerify(keyPair.getPublic());
        sha1withECDSA.update(src.getBytes());
        boolean verify = sha1withECDSA.verify(sign);
        System.out.println("JDK　ECDSA VERIFY:" + verify);
    }
}
