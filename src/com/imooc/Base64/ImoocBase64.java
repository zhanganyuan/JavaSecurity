package com.imooc.Base64;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Created by anyuan on 2016/11/21.
 */
public class ImoocBase64 {
    private static   String src = "imooc security base64";
    public static void main(String[] args) throws IOException {
        System.out.println("jdkBase64");
        jdkBase64();
        System.out.println("commonsCodesBase64");
        commonsCodesBase64();
        System.out.println("bouncyCastleBase64");
        bouncyCastleBase64();
    }

    private static void jdkBase64() throws IOException {
        BASE64Encoder encoder = new BASE64Encoder();
        String encode  = encoder.encode(src.getBytes());
        System.out.println("encode:"+encode);
        BASE64Decoder decoder = new BASE64Decoder();
        System.out.println(new String(decoder.decodeBuffer(encode)));
    }

    public static void commonsCodesBase64() {
        byte[] encodeByte = Base64.encodeBase64(src.getBytes());
        System.out.println("encode"+new String(encodeByte));
        byte[] decodeByte =  Base64.decodeBase64(encodeByte);
        System.out.println("decode:"+new String(decodeByte));

    }


    public  static void bouncyCastleBase64(){
        byte[] encodeBytes = org.bouncycastle.util.encoders.Base64.encode(src.getBytes());
        System.out.println("encode:"+new String(encodeBytes));
        byte[] decodeBytes = Base64.decodeBase64(encodeBytes);
        System.out.println("decode:"+new String(decodeBytes));
    }

}
