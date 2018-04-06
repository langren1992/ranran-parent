package com.ranran.system.operate.vo;

import java.util.List;

/**
 * 查询组件类
 * @author 曾睿
 * @create 2018-03-18 9:21
 **/
public class DroplistVo {

    private DroplistMainTableVo mainTable;
    private List<DroplistSubTableVo> joinTable;
    private List<DroplistColumsVo> columns;
    private List<DroplistConditionsVo> conditions;
    private String page;
    private String rows;

    public DroplistMainTableVo getMainTable() {
        return mainTable;
    }

    public void setMainTable(DroplistMainTableVo mainTable) {
        this.mainTable = mainTable;
    }

    public List<DroplistSubTableVo> getJoinTable() {
        return joinTable;
    }

    public void setJoinTable(List<DroplistSubTableVo> joinTable) {
        this.joinTable = joinTable;
    }

    public List<DroplistColumsVo> getColumns() {
        return columns;
    }

    public void setColumns(List<DroplistColumsVo> columns) {
        this.columns = columns;
    }

    public List<DroplistConditionsVo> getConditions() {
        return conditions;
    }

    public void setConditions(List<DroplistConditionsVo> conditions) {
        this.conditions = conditions;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }
}
