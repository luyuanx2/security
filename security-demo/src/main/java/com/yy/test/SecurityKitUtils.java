package com.yy.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 鲁源源 on 2017/11/1.
 */
public class SecurityKitUtils {

    public static String sha1(String str) {
        try {
            StringBuffer sb=new StringBuffer();
            MessageDigest messageDigest=MessageDigest.getInstance("sha1");
            messageDigest.update(str.getBytes());
            byte[] msg=messageDigest.digest();
            for (byte b : msg) {
                sb.append(String.format("%02x",b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
