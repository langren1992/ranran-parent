package com.ranran.system.operate.vo;

/**
 * 通用查询组件，主表查询类
 * @author 曾睿
 * @create 2018-03-18 9:13
 **/
public class DroplistMainTableVo {

    private String tableName;

    private String tableAlias;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }
}
