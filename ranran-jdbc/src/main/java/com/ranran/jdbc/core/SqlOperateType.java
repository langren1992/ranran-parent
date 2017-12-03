package com.ranran.jdbc.core;

public enum SqlOperateType {

    SELECT(0," SELECT "),
    FROM(1," FROM "),
    WHERE(2," WHERE "),
    UPDATE(3," UPDATE "),
    SET(4," SET "),
    INSERT(5," INSERT "),
    INTO(6," INTO "),
    DELETE(7," DELETE ");

    public final int code;
    public final String name;

    SqlOperateType(int code, String name){
        this.code = code;
        this.name = name;
    }

}
