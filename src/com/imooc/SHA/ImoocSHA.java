package com.imooc.SHA;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * Created by anyuan on 2016/11/22.
 */
public class ImoocSHA {
    public static String src = "imooc security sha";

    public static void main(String[] args) throws NoSuchAlgorithmException {
        jdkSHA1();
        bcSHA1();
        bcSHA224();
        bcSHA224_2();
        ccSHA1();

    }

    public static void jdkSHA1() throws NoSuchAlgorithmException {
        //SHA1的名称就是SHA
        MessageDigest md = MessageDigest.getInstance("SHA");
        //其实update也就是更新一下，正真执行数字签名的是后面的digest()
        //直接digest(src.getBytes())也行
        md.update(src.getBytes());
        System.out.println("JDK SHA1:" + Hex.encodeHexString(md.digest()));
    }

    public static void bcSHA1() {
        Digest digest = new SHA1Digest();
        digest.update(src.getBytes(), 0, src.getBytes().length);
        byte[] sha1Byte = new byte[digest.getDigestSize()];
        digest.doFinal(sha1Byte, 0);
        System.out.println("BC SHA1:" + org.bouncycastle.util.encoders.Hex.toHexString(sha1Byte));
    }

    public static void bcSHA224() {
        Digest digest = new SHA224Digest();
        digest.update(src.getBytes(), 0, src.getBytes().length);
        byte[] sha224Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(sha224Bytes, 0);
        System.out.println("BC SHA224:" + org.bouncycastle.util.encoders.Hex.toHexString(sha224Bytes));
    }

    public static void bcSHA224_2() throws NoSuchAlgorithmException {
        //先动态加载provider
        Security.addProvider(new BouncyCastleProvider());
        //之后和JDK一样
        MessageDigest md = MessageDigest.getInstance("SHA224");
        byte[] bcSHA224_2 = md.digest(src.getBytes());
        System.out.println(org.bouncycastle.util.encoders.Hex.toHexString(bcSHA224_2));
    }

    public static void ccSHA1() {
        //这里用sha1Hex而不只是sha1，一步到String
        System.out.println("CC SHA1:" + DigestUtils.sha1Hex(src.getBytes()));
    }

}
