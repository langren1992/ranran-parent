package com.ranran.core.jdbc;

import java.util.Arrays;

public class SelectCondition {

    //字段名
    private String fieldName;
    //操作类型
    private int operator=0; // 0: =; 1: >; 2 <;3>=;4<=; 5 not equal;
    //右边值
    private String rightValue;
    //左边值
    private String leftValue;
    //值
    private String[] values;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public String getRightValue() {
        return rightValue;
    }

    public void setRightValue(String rightValue) {
        this.rightValue = rightValue;
    }

    public String getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(String leftValue) {
        this.leftValue = leftValue;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "SelectCondition{" +
                "fieldName='" + fieldName + '\'' +
                ", operator=" + operator +
                ", rightValue='" + rightValue + '\'' +
                ", leftValue='" + leftValue + '\'' +
                ", values=" + Arrays.toString(values) +
                '}';
    }


}
