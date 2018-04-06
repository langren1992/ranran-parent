package com.ranran.system.operate.vo;

/**
 * 通用查询组件，从表查询类
 * @author 曾睿
 * @create 2018-03-18 9:13
 **/
public class DroplistSubTableVo {

    private String tableName;

    private String tableAlias;
    //从表关联类型
    private String joinType;
    // 从表关联键
    private String joinKey;

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
}
