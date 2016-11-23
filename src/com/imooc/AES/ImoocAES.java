package com.imooc.AES;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by anyuan on 2016/11/23.
 */
public class ImoocAES {
    public static final String src = "imooc security aes";

    public static void main(String[] args) {
        jdkAES();

    }

    public static void jdkAES() {
        //生成key
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            //key的转换其实上并不需要，至少在这里不需要。因为转换前后还是一样的东西。
            //之前是AES的KEY,之后也是转换AES的KEY
//            byte[] keyBytes = secretKey.getEncoded();

            //key转换
//            Key key = new SecretKeySpec(keyBytes, "AES");

            //加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("JDK AES ENCRYPT:" + Hex.encodeHexString(result));

            //解密
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            result = cipher.doFinal(result);
            System.out.println("JDK AES DECRYPT:" + new String(result));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }
}
