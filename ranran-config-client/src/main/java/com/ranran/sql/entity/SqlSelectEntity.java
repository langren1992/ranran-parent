package com.ranran.sql.entity;

import java.util.List;

public class SqlSelectEntity {

    private List<SelectColumnEntity> selectColumns;

    private FromTableEntity mainFromTable;

    private List<FromTableEntity> joinFromTables;

    private List<FromWhereEntity> fromWheres;

    private SelectGroupByEntity selectGroupBy;

    private SelectOrderByEntity selectOrderBy;

    private SelectLimitEntity selectLimit;

    public List<SelectColumnEntity> getSelectColumns() {
        return selectColumns;
    }

    public void setSelectColumns(List<SelectColumnEntity> selectColumns) {
        this.selectColumns = selectColumns;
    }

    public FromTableEntity getMainFromTable() {
        return mainFromTable;
    }

    public void setMainFromTable(FromTableEntity mainFromTable) {
        this.mainFromTable = mainFromTable;
    }

    public void setJoinFromTables(List<FromTableEntity> joinFromTables) {
        this.joinFromTables = joinFromTables;
    }

    public List<FromWhereEntity> getFromWheres() {
        return fromWheres;
    }

    public void setFromWheres(List<FromWhereEntity> fromWheres) {
        this.fromWheres = fromWheres;
    }

    public SelectGroupByEntity getSelectGroupBy() {
        return selectGroupBy;
    }

    public void setSelectGroupBy(SelectGroupByEntity selectGroupBy) {
        this.selectGroupBy = selectGroupBy;
    }

    public SelectOrderByEntity getSelectOrderBy() {
        return selectOrderBy;
    }

    public void setSelectOrderBy(SelectOrderByEntity selectOrderBy) {
        this.selectOrderBy = selectOrderBy;
    }

    public SelectLimitEntity getSelectLimit() {
        return selectLimit;
    }

    public void setSelectLimit(SelectLimitEntity selectLimit) {
        this.selectLimit = selectLimit;
    }

    @Override
    public String toString() {
        return "SqlSelectEntity{" +
                "selectColumns=" + selectColumns +
                ", mainFromTable=" + mainFromTable +
                ", joinFromTables=" + joinFromTables +
                ", fromWheres=" + fromWheres +
                ", selectGroupBy=" + selectGroupBy +
                ", selectOrderBy=" + selectOrderBy +
                ", selectLimit=" + selectLimit +
                '}';
    }
}
