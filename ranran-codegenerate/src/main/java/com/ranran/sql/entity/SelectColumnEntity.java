package com.ranran.sql.entity;

/**
 * @Title
 * @description 查询列
 * @author zengrui
 * @createTime 2018/4/1 9:46
 * @modifyTime 2018/4/1 9:46
 * @return
 **/
public class SelectColumnEntity {

    private String alias;

    private String name;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SelectColumnEntity{" +
                "alias='" + alias + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
