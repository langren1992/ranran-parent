package com.ranran.sql.entity;

/**
 * @Title
 * @description SQL from
 * @author zengrui
 * @createTime 2018/4/1 9:46
 * @modifyTime 2018/4/1 9:46
 * @return
 **/
public class FromTableEntity {

    private String name;

    private String alias;

    private String joinOrMain;

    private String joinType;

    private String joinKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getJoinOrMain() {
        return joinOrMain;
    }

    public void setJoinOrMain(String joinOrMain) {
        this.joinOrMain = joinOrMain;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

    public String getJoinKey() {
        return joinKey;
    }

    public void setJoinKey(String joinKey) {
        this.joinKey = joinKey;
    }


    @Override
    public String toString() {
        return "FromTableEntity{" +
                "name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", joinOrMain='" + joinOrMain + '\'' +
                ", joinType='" + joinType + '\'' +
                ", joinKey='" + joinKey + '\'' +
                '}';
    }
}
