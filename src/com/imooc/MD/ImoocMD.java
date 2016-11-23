package com.imooc.MD;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * Created by anyuan on 2016/11/21.
 */
public class ImoocMD {
    private static String src  = "imooc security md";

    public static void main(String[] args) throws NoSuchAlgorithmException {
        jdkMD2();
        jdkMD5();
        bcMD4();
        bcMD5();
        dynamicBCMD4();
        ccMD5();

    }

    public static void jdkMD5() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] md5Byte = md.digest(src.getBytes());
        //jdk中并没有实现把生成的byte数组转化成16进制
        //用apache.commons.codec里面的方法去转换
        System.out.println("JDK MD5:"+Hex.encodeHexString(md5Byte));
    }



    public static void jdkMD2() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD2");
        byte[] md2Byte = md.digest(src.getBytes());
        //jdk中并没有实现把生成的byte数组转化成16进制
        //用apache.commons.codec里面的方法去转换
        System.out.println("JDK MD2:"+Hex.encodeHexString(md2Byte));
    }

    public static void bcMD4(){
        Digest digest = new MD4Digest();
        digest.update(src.getBytes(),0,src.getBytes().length);
        byte[] md4Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(md4Bytes,0);
        System.out.println("BC MD4:"+ org.bouncycastle.util.encoders.Hex.toHexString(md4Bytes));
    }

    public static void bcMD5(){
        Digest digest  = new MD5Digest();
        digest.update(src.getBytes(),0,src.getBytes().length);
        byte[] md5Byte = new byte[digest.getDigestSize()];
        digest.doFinal(md5Byte,0);
        System.out.println("BC MD5:"+ org.bouncycastle.util.encoders.Hex.toHexString(md5Byte));
    }

    public static void dynamicBCMD4() throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        //如果是MD5而不是MD4
        MessageDigest md = MessageDigest.getInstance("MD5");
        //优先JDK
        System.out.println(md.getProvider());
        byte[] md4Bytes = md.digest(src.getBytes());
        System.out.println("DYNAMIC BC MD5:"+ org.bouncycastle.util.encoders.Hex.toHexString(md4Bytes));
    }


    //common codec相比JDK的更加方便
    //只是对JDK的MD5简化一些操作，本质上还是用JDK的实现
    public static void ccMD5(){
        System.out.println("CC MD5:"+DigestUtils.md5Hex(src.getBytes()));
    }
}
