package com.ranran.sql.entity;

public class SelectGroupByEntity {
    private String groupBy;

    private String having;

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public String getHaving() {
        return having;
    }

    public void setHaving(String having) {
        this.having = having;
    }

    @Override
    public String toString() {
        return "SelectGroupByEntity{" +
                "groupBy='" + groupBy + '\'' +
                ", having='" + having + '\'' +
                '}';
    }
}
