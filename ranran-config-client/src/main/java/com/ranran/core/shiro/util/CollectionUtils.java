package com.ranran.core.shiro.util;

import java.util.Collection;

/**
 * Created by zengrui on 2017/7/18.
 */
public class CollectionUtils {

    public static boolean isEmpty(Collection coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isNotEmpty(Collection coll) {
        return !isEmpty(coll);
    }
}
