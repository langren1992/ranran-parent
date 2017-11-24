package com.ranran.codegenerate.sql;

import java.util.LinkedList;
import java.util.List;

/**
 * 表结构信息
 *
 * @author 曾睿
 * @create 2017-11-23 16:10
 **/
public class TableInfo {

    private String name;

    private String comment;

    private String className;


    public TableInfo(String name, String className, String comment) {
        this.name = name;
        this.comment = comment;
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    private List<ColumnInfo> idColumns = new LinkedList<ColumnInfo>();

    private List<ColumnInfo> columns = new LinkedList<ColumnInfo>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<ColumnInfo> getIdColumns() {
        return idColumns;
    }

    public void setIdColumns(List<ColumnInfo> idColumns) {
        this.idColumns = idColumns;
    }

    public List<ColumnInfo> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnInfo> columns) {
        this.columns = columns;
    }

}
