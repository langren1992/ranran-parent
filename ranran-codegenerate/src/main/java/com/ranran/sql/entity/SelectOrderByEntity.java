package com.ranran.sql.entity;

import java.util.List;

public class SelectOrderByEntity {
    private String orderBy;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        return "SelectOrderByEntity{" +
                "orderBy='" + orderBy + '\'' +
                '}';
    }
}
