package com.qdtckj.component.commons.amount;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.math.BigDecimal;

/**
 * Created by zhangzhenguo on 2017/8/2.
 * 价格转换工具
 */

public class PriceUtil {

    public static final Double ZERO = 0D;
    private static final Integer SCALE = 2;
    private static final Double MIN = 0.01;

    @SuppressLint("DefaultLocale")
    public static String chartFormatData(Double data) {
        if (data == null){
            return "0.00";
        }
        return String.format("%.2f", data);
    }

    @SuppressLint("DefaultLocale")
    public static String chartFormatInt(Double data){
        if (data == null){
            return "0";
        }
        return String.format("%.0f", data);
    }

    /**
     * string转double
     * @param string 字符串
     * @param defaultValue 默认值
     * @return double
     */
    public static double string2double(String string,double defaultValue){
        if (TextUtils.isEmpty(string)){
            return defaultValue;
        }
        try {
            return Double.valueOf(string);
        }catch (Exception e){
        }
        return defaultValue;
    }

    /**
     * 小于
     * @param amount1 第一个金额
     * @param amount2 第二个金额
     * @return 第一个金额是否小于第二个金额
     */
    public static boolean less(Double amount1, Double amount2) {
        if(amount1 == null && amount2 == null) {
            return false;
        }

        if(amount2 == null) {
            return false;
        }
        if(amount1 == null) {
            return  true;
        }

        Double d = noNullSubtract(amount1, amount2);
        return d < ZERO;
    }

    /**
     * 小于等于
     * @param amount1 第一个金额
     * @param amount2 第二个金额
     * @return 第一个金额是否小于等于第二个金额
     */
    public static boolean lessEquals(Double amount1, Double amount2) {
        if(amount1 == null && amount2 == null) { // 都是null
            return true;
        }

        if(amount2 == null) { // 第一个不是null,第二个是null
            return false;
        }
        if(amount1 == null) { // 第一个是null,第二个不是null
            return true;
        }

        // 都不null
        Double d = noNullSubtract(amount1, amount2);
        return d <= ZERO;
    }

    /**
     * 大于
     * @param amount1 第一个金额
     * @param amount2 第二个金额
     * @return 第一个金额是否大于第二个金额
     */
    public static boolean greater(Double amount1, Double amount2) {
        if(amount1 == null && amount2 == null) {
            return false;
        }

        if(amount2 == null) {
            return true;
        }
        if(amount1 == null) {
            return  false;
        }

        Double d = noNullSubtract(amount1, amount2);
        return d > ZERO;
    }

    /**
     * 大于等于
     * @param amount1 第一个金额
     * @param amount2 第二个金额
     * @return 第一个金额是否大于等于第二个金额
     */
    public static boolean greaterEquals(Double amount1, Double amount2) {
        if(amount1 == null && amount2 == null) {
            return true;
        }

        if(amount2 == null) {
            return true;
        }
        if(amount1 == null) {
            return  false;
        }

        Double d = noNullSubtract(amount1, amount2);
        return d >= ZERO;
    }

    public static boolean equals(Double amount1, Double amount2) {
        if(amount1 == null && amount2 == null) {
            return true;
        }

        if(amount2 == null) {
            return true;
        }
        if(amount1 == null) {
            return  false;
        }

        Double d = noNullSubtract(amount1, amount2);
        return d == ZERO;
    }

    /**
     * 金额相加
     * @param amount1 金额1
     * @param amount2 金额2
     * @return 总和
     */
    public static Double plus(Double amount1, Double amount2) {
        if(amount1 == null && amount2 == null) {
            return ZERO;
        }

        if(amount2 == null) {
            return noNullSubtract(amount1);
        }
        if(amount1 == null) {
            return  noNullSubtract(amount2);
        }

        return new BigDecimal(amount1).add(new BigDecimal(amount2)).setScale(SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 金额相减,第一个金额不能比第二个金额小
     * @param amount1 金额1
     * @param amount2 金额2
     * @return 相减后的金额
     */
    public static Double subtract(Double amount1, Double amount2) {
        if(amount1 == null && amount2 == null) {
            return ZERO;
        }

        if(amount2 == null) {
            return noNullSubtract(amount1);
        }
        if(amount1 == null) {
            return  noNullSubtract(-1* amount2);
        }

        return noNullSubtract(amount1,amount2);
    }

    private static Double noNullSubtract(Double amount1, Double amount2) {
        Double d = new BigDecimal(amount1).subtract(new BigDecimal(amount2)).setScale(SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
        if(Math.abs(d) < MIN) {
            return ZERO;
        }
        return d;
    }

    private static Double noNullSubtract(Double amount) {
        Double d = new BigDecimal(amount).setScale(SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
        if(Math.abs(d) < MIN) {
            return ZERO;
        }
        return d;
    }

}
