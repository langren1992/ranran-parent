package com.ranran.core;

import javax.persistence.Transient;

/**
 * Created by zengrui on 2017/7/18.
 */
public class BaseModel {

    @Transient
    private Integer page;

    @Transient
    private Integer rows;

    @Transient
    private String sort;

    @Transient
    private String order;

    @Transient
    private Integer optType;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getOptType() {
        return optType;
    }

    public void setOptType(Integer optType) {
        this.optType = optType;
    }

    public static enum OrderEnum{
        ASC("ASC",1),DESC("DESC",2);

        public final String name;

        public final int code;

        OrderEnum(String name,int code){
            this.code = code;
            this.name = name;
        }
    }
}
