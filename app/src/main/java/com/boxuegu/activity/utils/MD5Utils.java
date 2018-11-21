package com.boxuegu.activity.utils;

import android.content.Intent;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2018/9/13 0013.
 * 第三次创建了，我也是醉了
 */

public class MD5Utils {

    public  static  String md5(String message){
        try {
            StringBuffer buffer = new StringBuffer();
            MessageDigest md=MessageDigest.getInstance("MD5");
            byte[] reslut=md.digest(message.getBytes());
            for(byte b:reslut){
                int number=b&0xff;
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            return buffer.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }


    }

}
