package com.qdtckj.component.commons.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangzhenguo on 2017/11/15.
 * 验证工具
 */

public class RegularUtils {

    /**
     * 验证正则表达式
     * @param regex 正则规则
     * @param str 要验证的字符串
     * @return true or false
     */
    public static boolean doMatch(String regex,String str){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
