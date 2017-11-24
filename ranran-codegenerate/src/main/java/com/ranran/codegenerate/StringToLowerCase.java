package com.ranran.codegenerate;

import org.beetl.core.Context;
import org.beetl.core.Function;

/**
 * Created by zengrui on 2017/6/11.
 */
public class StringToLowerCase implements Function {
    public Object call(Object[] objects, Context context) {
        String str = (String) objects[0];
        return Character.isLowerCase(str.charAt(0))?str:Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }
}
