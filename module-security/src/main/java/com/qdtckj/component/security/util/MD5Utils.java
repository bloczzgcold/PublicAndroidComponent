/*
 * Copyright (c) 2015. 半岛传媒网
 * Author:Hiviiup
 * Time: 2015
 * Description:
 */

package com.qdtckj.component.security.util;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    /**
     * 使用md5的算法进行加密
     *
     * @param plainText 需要加密的字符串
     * @return 加密密文
     */
    public static String md5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        return new BigInteger(1, secretBytes).toString(16);
    }
}
