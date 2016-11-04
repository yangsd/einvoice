package com.core.util;

/**
 * Created by sdyang on 2016/10/13.
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String getValue(String str){
        if(isEmpty(str)) return "";
        return str;
    }
}
