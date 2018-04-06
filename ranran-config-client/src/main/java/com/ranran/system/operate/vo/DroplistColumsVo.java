package com.ranran.system.operate.vo;

/**
 * 查询的列表结果
 * @author 曾睿
 * @create 2018-03-18 9:18
 **/
public class DroplistColumsVo {

    private String field;

    private String columnAlias;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getColumnAlias() {
        return columnAlias;
    }

    public void setColumnAlias(String columnAlias) {
        this.columnAlias = columnAlias;
    }
}
