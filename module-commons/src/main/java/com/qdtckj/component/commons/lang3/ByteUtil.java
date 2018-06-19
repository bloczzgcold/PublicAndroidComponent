package com.qdtckj.component.commons.lang3;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by zhangzhenguo on 2017/11/10.
 * 字节操作工具类
 */

public class ByteUtil {

    private static final String CHARSET = "UTF-8";

    /**
     * 10进制转字节
     *
     * @param i int
     * @return byte
     */
    public static byte int2Byte(int i) {
        byte r = (byte) i;
        return r;
    }

    /**
     * 字节数组转字符串
     *
     * @param b       byte[]数组
     * @param charset 编码集
     * @return string字符串
     * @throws Exception 异常
     */
    public static String bytes2String(byte[] b, String charset) throws Exception {
        if (charset == null || "".equals(charset)) {
            charset = CHARSET;
        }
        String r = new String(b, charset);
        return r;
    }

    /**
     * 字符串转byte[]，默认编码集UTF-8
     *
     * @param str     字符串
     * @param charset 编码集
     * @return byte[]
     */
    public static byte[] string2Bytes(String str, String charset) {
        if (str == null) {
            return null;
        }
        if (charset == null || "".equals(charset)) {
            charset = "UTF-8";
        }
        byte[] byteArray = new byte[0];
        try {
            byteArray = str.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return byteArray;
    }

    /**
     * 字节数组转16进制字符串
     *
     * @param b byte[]
     * @return 16进制字符串
     */
    public static String bytes2HexString(byte[] b) {
        String r = "";
        if (b == null) {
            return "";
        }
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            r += hex.toUpperCase();
        }

        return r;
    }

    /**
     * byte[]转16进制字符串
     *
     * @param b      byte[]
     * @param length byte[]长度值
     * @return 16进制字符串
     */
    public static String bytes2HexString(byte[] b, int length) {
        String ret = "";
        for (int i = 0; i < length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }

    /**
     * 字符转换为字节
     *
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 16进制字符串转字节数组
     *
     * @param hex 16进制字符串
     * @return byte[]
     */
    public static byte[] hexString2Bytes(String hex) {

        if ((hex == null) || (hex.equals(""))) {
            return null;
        } else if (hex.length() % 2 != 0) {
            return null;
        } else {
            hex = hex.toUpperCase();
            int len = hex.length() / 2;
            byte[] b = new byte[len];
            char[] hc = hex.toCharArray();
            for (int i = 0; i < len; i++) {
                int p = 2 * i;
                b[i] = (byte) (charToByte(hc[p]) << 4 | charToByte(hc[p + 1]));
            }
            return b;
        }

    }

    /**
     * 16进制字符串转字符串
     *
     * @param hex     16进制字符串
     * @param charset 编码集
     * @return 字符串
     * @throws Exception 异常
     */
    public static String hex2String(String hex, String charset) throws Exception {
        String r = bytes2String(hexString2Bytes(hex), charset);
        return r;
    }

    /**
     * 字符串转16进制字符串
     *
     * @param s       待转字符串
     * @param charset 编码集
     * @return 16进制数据
     * @throws Exception 异常
     */
    public static String string2HexString(String s, String charset) throws Exception {
        String r = bytes2HexString(string2Bytes(s, charset));
        return r;
    }

    /**
     * byte[] to binary string
     *
     * @param bytes 需要转的byte[]
     * @return string
     */
    public static String byte2BinaryString(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            result.append(Long.toBinaryString((bytes[i] & 0xFF) + 0x100).substring(1));
        }
        return result.toString();
    }

    /**
     * byte[] 转int
     *
     * @param b byte[]
     * @return int
     */
    public static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    /**
     * int 转 byte[]
     *
     * @param a int
     * @return byte[]
     */
    public static byte[] intToBytes(int a) {
        return new byte[]{
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }

    /**
     * 将String转成BCD码
     *
     * @param s 待转字符串
     * @return 字节数组
     */
    public static byte[] strToBCDBytes(String s) {
        if (s.length() % 2 != 0) {
            s = "0" + s;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i += 2) {
            int high = cs[i] - 48;
            int low = cs[i + 1] - 48;
            baos.write(high << 4 | low);
        }
        return baos.toByteArray();
    }

    /**
     * 将BCD码转成int
     *
     * @param b byte[]
     * @return int
     */
    public static int bcdToint(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            int h = ((b[i] & 0xff) >> 4) + 48;
            sb.append((char) h);
            int l = (b[i] & 0x0f) + 48;
            sb.append((char) l);
        }
        return Integer.parseInt(sb.toString());
    }

    /**
     * bcd转long
     *
     * @param b bcd byte[]
     * @return long
     */
    public static long bcd2Long(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            int h = ((b[i] & 0xff) >> 4) + 48;
            sb.append((char) h);
            int l = (b[i] & 0x0f) + 48;
            sb.append((char) l);
        }
        return Long.parseLong(sb.toString());
    }

    private final static char[] BToA = "0123456789abcdef".toCharArray();

    /**
     * BCD码转ASC码
     *
     * @param bytes BCD串
     * @return : ASC码
     */
    public static String bcd2asc(byte[] bytes) {
        StringBuffer temp = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            int h = ((bytes[i] & 0xf0) >>> 4);
            int l = (bytes[i] & 0x0f);
            temp.append(BToA[h]).append(BToA[l]);
        }
        return temp.toString();
    }

    /**
     * byte[] 右补齐 值为b
     *
     * @param value  源数组
     * @param length 补齐后的长度
     * @param b      补的值
     * @return 新的byte[]
     */
    public static byte[] bytesPadRight(byte[] value, int length, byte b) {
        if (value.length == length) {
            return value;
        }
        byte[] newByte = new byte[length];
        System.arraycopy(value, 0, newByte, 0, value.length);
        for (int i = value.length; i < length; i++) {
            newByte[i] = b;
        }
        return newByte;
    }

    /**
     * byte[] 左补齐 值为b
     *
     * @param value  源数组
     * @param length 补齐后的长度
     * @param b      补的值
     * @return 新的byte[]
     */
    public static byte[] bytesPadLeft(byte[] value, int length, byte b) {
        if (value.length == length) {
            return value;
        }
        byte[] newByte = new byte[length];
        for (int i = 0; i < length - value.length; i++) {
            newByte[i] = b;
        }
        System.arraycopy(value, 0, newByte, length - value.length, value.length);
        return newByte;
    }

    /**
     * 根据索引坐标截取byte[]
     *
     * @param value      要截取的byte[]
     * @param startIndex 起始索引
     * @param endIndex   结束索引
     * @return 截取后的byte[]
     */
    public static byte[] getSubBytesByIndex(byte[] value, int startIndex, int endIndex) {
        if (value.length < endIndex + 1) {
            return value;
        }
        byte[] newByte = new byte[endIndex - startIndex + 1];
        for (int i = startIndex; i <= endIndex; i++) {
            newByte[i - startIndex] = value[i];
        }
        return newByte;
    }

    /**
     * trim掉左边字节值为b的字节
     *
     * @param b     字节数组
     * @param value 待trim字符
     * @return 字节数组
     */
    public static byte[] bytesTrimLeft(byte[] value, byte b) {
        int index = 0;
        for (int i = 0; i < value.length; i++) {
            if (b == value[i]) {
                index++;
            } else {
                break;
            }
        }
        byte[] newBytes = new byte[value.length - index];
        System.arraycopy(value, index, newBytes, 0, value.length - index);
        return newBytes;
    }

    public static long readBCD(byte[] value, int dataLength, int lengthBytesLength) {
        byte[] subBytesByIndex = getSubBytesByIndex(value, dataLength, dataLength + lengthBytesLength - 1);
        return bcd2Long(subBytesByIndex);
    }

    public static long readLong(byte[] value, int dataLength, int lengthBytesLength) {
        byte[] subBytesByIndex = getSubBytesByIndex(value, dataLength, dataLength + lengthBytesLength - 1);
        long l = bcd2Long(subBytesByIndex);
        l = l * 2;
        return l;
    }

    public static String readString(byte[] iso8583FieldsBytes, int idx, int lengthBytesLength) {
        return null;
    }

    public static void writeBCD(byte[] bytes, int i, int dataLength, int lengthBytesLength) {
        byte[] bytes1 = strToBCDBytes(String.valueOf(dataLength));
        bytes1 = bytesPadLeft(bytes1, lengthBytesLength, (byte) 0);
        System.arraycopy(bytes1, 0, bytes, 0, lengthBytesLength);
    }

    public static void writeLong(byte[] bytes, int i, int dataLength, int lengthBytesLength) {
        byte[] bytes1 = strToBCDBytes(String.valueOf(dataLength));
        bytes1 = bytesPadLeft(bytes1, lengthBytesLength, (byte) 0);
        System.arraycopy(bytes1, 0, bytes, 0, lengthBytesLength * 2);
    }

    public static void writeString(byte[] bytes, int i, String s, int lengthBytesLength) {
    }

    /**
     * asc byte to bcd byte
     *
     * @param asc asc byte
     * @return bcd byte
     */
    private static byte asc_to_bcd(byte asc) {
        byte bcd;

        if ((asc >= '0') && (asc <= '9'))
            bcd = (byte) (asc - '0');
        else if ((asc >= 'A') && (asc <= 'F'))
            bcd = (byte) (asc - 'A' + 10);
        else if ((asc >= 'a') && (asc <= 'f'))
            bcd = (byte) (asc - 'a' + 10);
        else if (asc == '=')
            bcd = (byte) (asc - 48);
        else
            bcd = (byte) (asc - 48);
        return bcd;
    }

    /**
     * asc[] to bdc[]
     *
     * @param ascii   asc[]
     * @param asc_len asc length
     * @return bcd[]
     */
    public static byte[] ASCII2BCD(byte[] ascii, int asc_len) {
        byte[] bcd = new byte[asc_len / 2];
        int j = 0;
        for (int i = 0; i < (asc_len + 1) / 2; i++) {
            bcd[i] = asc_to_bcd(ascii[j++]);
            bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));
        }
        return bcd;
    }

    /**
     * asc to bcd
     *
     * @param asc asc码
     * @return bcd码
     */
    public static byte[] asc2Bcd(String asc) {
        int len = asc.length();
        int mod = len % 2;

        if (mod != 0) {
            asc = "0" + asc;
            len = asc.length();
        }

        byte abt[] = new byte[len];
        if (len >= 2) {
            len = len / 2;
        }

        byte bbt[] = new byte[len];
        abt = asc.getBytes();
        int j, k;

        for (int p = 0; p < asc.length() / 2; p++) {
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
                j = abt[2 * p] - '0';
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
                j = abt[2 * p] - 'a' + 0x0a;
            } else {
                j = abt[2 * p] - 'A' + 0x0a;
            }

            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
                k = abt[2 * p + 1] - '0';
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
                k = abt[2 * p + 1] - 'a' + 0x0a;
            } else {
                k = abt[2 * p + 1] - 'A' + 0x0a;
            }

            int a = (j << 4) + k;
            byte b = (byte) a;
            bbt[p] = b;
        }
        return bbt;
    }

    public static String BCD2Str(byte[] bytes) {
        char temp[] = new char[bytes.length * 2], val;

        for (int i = 0; i < bytes.length; i++) {
            val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
            temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

            val = (char) (bytes[i] & 0x0f);
            temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
        }
        return new String(temp);
    }

    /**
     * 异或运算
     *
     * @param src 字符串
     * @param key key
     * @return 异或后的值
     */
    public static String xorBit(String src, String key) {
        if (src.length() != key.length()) {
            throw new RuntimeException("参数长度不一致");
        }
        int len = src.length() / 2;
        StringBuilder tempRes = new StringBuilder();
        for (int j = 1; j <= len; j++) {
            int x = Integer.parseInt(src.substring(j * 2 - 2, j * 2), 16);
            int y = Integer.parseInt(key.substring(j * 2 - 2, j * 2), 16);
            int z = x ^ y;
            String string = Integer.toHexString(z);
            if (string.length() < 2) {
                string = "0" + string;
            }
            tempRes.append(string);
        }
        return tempRes.toString();
    }

    /**
     * byte[]截取
     *
     * @param src   源数组
     * @param begin 开始索引
     * @param count 长度
     * @return 截取后的数组
     */
    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        System.arraycopy(src, begin, bs, 0, count);
        return bs;
    }

}
