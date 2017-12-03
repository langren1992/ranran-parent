package com.ranran.jdbc.core;

public enum ConditionOperateType {

    EQUAL(0,"="),
    GREAT_THAN(1,">"),
    LESS_THAN(2,"<"),
    GREAT_EQUAL(3,">="),
    LESS_EQUAL(4,"<="),
    NOT_EQUAL(5,"!="),
    BETWEEN(6,"between"),
    IN(7,"in"),
    LIKE(8,"like"),
    LEFT_LIKE(9,"%like"),
    RIGHT_LIKE(9,"like%");

    public final int code;
    public final String name;

    ConditionOperateType(int code, String name){
        this.code = code;
        this.name = name;
    }

}
