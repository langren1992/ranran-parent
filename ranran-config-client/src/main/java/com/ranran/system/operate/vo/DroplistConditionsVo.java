package com.ranran.system.operate.vo;

/**
 * 下拉列表查询条件
 * @author 曾睿
 * @create 2018-03-18 16:34
 **/
public class DroplistConditionsVo {
    private String field;
    private String value;
    private String fieldAlias;
    private String logicOpt;
    private String compareOpt;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFieldAlias() {
        return fieldAlias;
    }

    public void setFieldAlias(String fieldAlias) {
        this.fieldAlias = fieldAlias;
    }

    public String getLogicOpt() {
        return logicOpt;
    }

    public void setLogicOpt(String logicOpt) {
        this.logicOpt = logicOpt;
    }

    public String getCompareOpt() {
        return compareOpt;
    }

    public void setCompareOpt(String compareOpt) {
        this.compareOpt = compareOpt;
    }
}
