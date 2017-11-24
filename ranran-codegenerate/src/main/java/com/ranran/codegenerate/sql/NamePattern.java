package com.ranran.codegenerate.sql;

/**
 * 命名类型
 *
 * @author 曾睿
 * @create 2017-11-23 18:08
 **/
public abstract class NamePattern {

    public NamePattern() {
    }

    public abstract String getTableName(Class<?> var1);

    public String getClassName(String tableName) {
        return tableName;
    }

    public String getColName(String attrName) {
        return this.getColName((Class)null, attrName);
    }

    public abstract String getColName(Class<?> var1, String var2);

    public abstract String getPropertyName(Class<?> var1, String var2);

    public String getPropertyName(String colName) {
        return this.getPropertyName((Class)null, colName);
    }


}
