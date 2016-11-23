package com.imooc.HMAC;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by anyuan on 2016/11/22.
 */
public class ImoocHMAC {
    public static String src = "imooc security hmac";
    public static void main(String[] args) {
        jdkHmacMD5();
        bcHmacMD5();
    }

    private static void jdkHmacMD5() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");//初始化KeyGenerator
            SecretKey secretKry = keyGenerator.generateKey();//产生密钥
//            byte[] key  = secretKry.getEncoded();//获得密钥
            byte[] key = Hex.decodeHex(new char[] {'a','a','a','a'});

            SecretKey restoreSecretKey = new SecretKeySpec(key,"HmacMD5");//还原密钥
            Mac mac = Mac.getInstance(restoreSecretKey.getAlgorithm());//实例化mac
            mac.init(restoreSecretKey);//初始化mac
            byte[] hmacMD5Bytes = mac.doFinal(src.getBytes());//执行摘要
            System.out.println("JDK HmacMD5:"+ Hex.encodeHexString(hmacMD5Bytes));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (DecoderException e) {
            e.printStackTrace();
        }
    }


    public static void bcHmacMD5(){
        HMac hmac  = new HMac(new MD5Digest());
        hmac.init(new KeyParameter(org.bouncycastle.util.encoders.Hex.decode("aaaa")));
        hmac.update(src.getBytes(),0,src.getBytes().length);
        byte[] hmacMD5Bytes = new byte[hmac.getMacSize()];
        hmac.doFinal(hmacMD5Bytes,0);
        System.out.println("BC HmacMD5:"+org.bouncycastle.util.encoders.Hex.toHexString(hmacMD5Bytes));
    }


}
