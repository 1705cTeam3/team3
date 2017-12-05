package com.jk.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.shiro.crypto.hash.Md5Hash;

import sun.misc.BASE64Encoder;

public class Md5Util {
    public static String EncoderByMd5(String str){
     MessageDigest md5;
     BASE64Encoder base64en = new BASE64Encoder();
     String newstr="";
	try {
		md5 = MessageDigest.getInstance("MD5");
		newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
        return newstr;
        }
    
    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            String md5=new BigInteger(1, md.digest()).toString(16);
            //BigInteger会把0省略掉，需补全至32位
            return fillMD5(md5);
        } catch (Exception e) {
            throw new RuntimeException("MD5加密错误:"+e.getMessage(),e);
        }
    }
    public static String fillMD5(String md5){
        return md5.length()==32?md5:fillMD5("0"+md5);
    }

    public static String createPassword(String password, String salt, int hashIterations) {
        Md5Hash md5Hash = new Md5Hash(password.trim(), salt, hashIterations);
        return md5Hash.toString();
    }

	
}
