package com.qdtckj.component.commons.text;

/**
 * Created by zhangzhenguo on 2018/4/26.
 * text format
 */

public class TextTool {
    public static final String EMPTY = "";

    /**
     *  format null value to empty string
     *  TextTools.formatEmpty(null)     = ""
     *  TextTools.formatEmpty("")       = ""
     *  TextTools.formatEmpty("123")    = "123"
     *
     * @param value the string to format, may be null
     * @return string
     */
    public static String formatEmpty(String value){
        return value == null ? EMPTY : value;
    }

    /**
     * format null value to spare
     * TextTool.formatNullToSpare(null,"message")     =  "message"
     * TextTool.formatNullToSpare("","message")       =  ""
     * TextTool.formatNullToSpare("123","message")    =  "123"
     * @param value the string to format, may be null
     * @param spare spare string text
     * @return string
     */
    public static String formatNullToSpare(String value,String spare){
        return value == null ? spare : value;
    }

    /**
     * format empty value to spare
     * TextTool.formatEmptyToSpare(null,"message")      = "message"
     * TextTool.formatEmptyToSpare("", "message")       = "message"
     * TextTool.formatEmptyToSpare("123", "message")    = "123"
     * @param value the string to format, may be empty
     * @param spare spare string text
     * @return string
     */
    public static String formatEmptyToSpare(String value,String spare){
        return value == null || EMPTY.equals(value) ? spare : value;
    }
}
