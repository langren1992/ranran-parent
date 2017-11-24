package com.ranran.codegenerate;

/**
 * Created by zengrui on 2017/6/11.
 */
public class StringUtils {

    public static String toLowerCaseFirstOne(String s) {
        return Character.isLowerCase(s.charAt(0))?s:Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }

    public static String toUpperCaseFirstOne(String s) {
        return Character.isUpperCase(s.charAt(0))?s:Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    public static String enCodeUnderlined(String s) {
        char[] chars = toLowerCaseFirstOne(s).toCharArray();
        StringBuilder temp = new StringBuilder();

        for(int i = 0; i < chars.length; ++i) {
            if(Character.isUpperCase(chars[i])) {
                temp.append("_");
            }

            temp.append(Character.toLowerCase(chars[i]));
        }

        return temp.toString();
    }

    public static String deCodeUnderlined(String str) {
        String[] splitArr = str.split("_");
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < splitArr.length; ++i) {
            if(i == 0) {
                sb.append(splitArr[0].toLowerCase());
            } else {
                sb.append(toUpperCaseFirstOne(splitArr[i].toLowerCase()));
            }
        }

        return sb.toString();
    }


}
