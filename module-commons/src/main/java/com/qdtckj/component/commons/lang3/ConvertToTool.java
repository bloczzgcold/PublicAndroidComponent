package com.qdtckj.component.commons.lang3;

/**
 * Created by zhangzhenguo on 2018/6/21.
 * 转换工具类
 */

public class ConvertToTool {

    /**
     * 字符串转整数
     *
     * @param str 待转化字符串
     * @param defValue 默认值
     * @return int
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception ignored) {
        }
        return defValue;
    }

    /**
     * 对象转整数
     *
     * @param obj 待转化对象
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * 对象转整数
     *
     * @param obj 待转化对象
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception ignored) {
        }
        return 0;
    }

    /**
     * 对象转double
     *
     * @param obj 待转化对象
     * @return 转换异常返回 0
     */
    public static double toDouble(String obj) {
        try {
            return Double.parseDouble(obj);
        } catch (Exception ignored) {
        }
        return 0;
    }

    /**
     * 字符串转布尔值
     *
     * @param str 待转化字符串
     * @return 转换异常返回 false
     */
    public static boolean toBool(String str) {
        try {
            return Boolean.parseBoolean(str);
        } catch (Exception ignored) {
        }
        return false;
    }
}
