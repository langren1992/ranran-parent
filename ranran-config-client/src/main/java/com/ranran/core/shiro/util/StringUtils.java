package com.ranran.core.shiro.util;

/**
 * Created by zengrui on 2017/7/18.
 */
public class StringUtils {

    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(Object str) {
        return !StringUtils.isEmpty(str);
    }
}
