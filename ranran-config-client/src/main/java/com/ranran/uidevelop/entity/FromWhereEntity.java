package com.ranran.uidevelop.entity;

public class FromWhereEntity {

    private String column;

    private String compareType;

    private String value;

    private String logicType;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getCompareType() {
        return compareType;
    }

    public void setCompareType(String compareType) {
        this.compareType = compareType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLogicType() {
        return logicType;
    }

    public void setLogicType(String logicType) {
        this.logicType = logicType;
    }

    @Override
    public String toString() {
        return "FromWhereEntity{" +
                "column='" + column + '\'' +
                ", compareType='" + compareType + '\'' +
                ", value='" + value + '\'' +
                ", logicType='" + logicType + '\'' +
                '}';
    }
}
