package com.imooc.DES;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by anyuan on 2016/11/23.
 */
public class Imooc3DES {

    public static final String src = "imooc security des";

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        jdk3DES();

    }

    public static void jdk3DES() throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        //生成Key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
//        keyGenerator.init(168);
        keyGenerator.init(new SecureRandom());
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] bytesKey = secretKey.getEncoded();

        //Key的转换
        DESedeKeySpec desKeySpec = new DESedeKeySpec(bytesKey);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
        SecretKey convertSecretKey = factory.generateSecret(desKeySpec);

        //加密
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
        byte[] result = cipher.doFinal(src.getBytes());
        System.out.println("JDK 3DES ENCRYPT:" + Hex.encodeHexString(result));

        //解密
        cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
        result = cipher.doFinal(result);
        System.out.println("JDK 3DES DECRYPT:" + new String(result));
    }




}
