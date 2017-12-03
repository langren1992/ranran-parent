package com.ranran.jdbc.core;

import java.util.Arrays;

public class SelectCondition {

    //字段名
    private String fieldName;
    //操作类型
    private ConditionOperateType operator=ConditionOperateType.EQUAL; // 0: =; 1: >; 2 <;3>=;4<=; 5 not equal;
    //右边值
    private String rightValue;
    //左边值
    private String leftValue;
    //值
    private Object[] values;
    private Object value;

    public SelectCondition() {
    }

    public SelectCondition(String fieldName, Object value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public ConditionOperateType getOperator() {
        return operator;
    }

    public void setOperator(ConditionOperateType operator) {
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

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SelectCondition{" +
                "fieldName='" + fieldName + '\'' +
                ", operator=" + operator +
                ", rightValue='" + rightValue + '\'' +
                ", leftValue='" + leftValue + '\'' +
                ", values=" + Arrays.toString(values) +
                ", value='" + value + '\'' +
                '}';
    }
}
