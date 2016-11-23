package com.imooc.DES;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by anyuan on 2016/11/23.
 */
public class ImoocDES {
    public static final String src = "imooc security des";

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException, NoSuchProviderException {
        jdkDES();
        bcDES();

    }

    public static void jdkDES() throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        //生成Key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] bytesKey = secretKey.getEncoded();

        //Key的转换
        DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
        SecretKey convertSecretKey = factory.generateSecret(desKeySpec);

        //加密
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
        byte[] result = cipher.doFinal(src.getBytes());
        System.out.println("JDK DES ENCRYPT:" + Hex.encodeHexString(result));

        //解密
        cipher.init(Cipher.DECRYPT_MODE,convertSecretKey);
        result = cipher.doFinal(result);
        System.out.println("JDK DES DECRYPT:"+new String(result));


    }



    public static void bcDES() throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, NoSuchProviderException {
        Security.addProvider(new BouncyCastleProvider());

        //生成Key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES","BC");//指定BC
        SecretKey secretKey = keyGenerator.generateKey();
        System.out.println(keyGenerator.getProvider());
        byte[] bytesKey = secretKey.getEncoded();

        //Key的转换
        DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
        SecretKey convertSecretKey = factory.generateSecret(desKeySpec);

        //加密
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
        byte[] result = cipher.doFinal(src.getBytes());
        System.out.println("BC DES ENCRYPT:" + Hex.encodeHexString(result));

        //解密
        cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
        result = cipher.doFinal(result);
        System.out.println("BC DES DECRYPT:" + new String(result));

    }

    }
