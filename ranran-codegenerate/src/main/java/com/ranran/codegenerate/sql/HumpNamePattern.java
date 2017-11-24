package com.ranran.codegenerate.sql;

import com.ranran.codegenerate.StringUtils;

/**
 * Created by zengrui on 2017/6/11.
 */
public class HumpNamePattern extends NamePattern{

    public HumpNamePattern() {
    }

    @Override
    public String getTableName(Class<?> var1) {
        return null;
    }

    public String getClassName(String tableName) {
        String temp = StringUtils.deCodeUnderlined(tableName.toLowerCase());
        return StringUtils.toUpperCaseFirstOne(temp);
    }

    @Override
    public String getColName(Class<?> c, String attrName) {
        return StringUtils.enCodeUnderlined(attrName);
    }

    @Override
    public String getPropertyName(Class<?> c, String colName) {
        return StringUtils.deCodeUnderlined(colName.toLowerCase());
    }
}
