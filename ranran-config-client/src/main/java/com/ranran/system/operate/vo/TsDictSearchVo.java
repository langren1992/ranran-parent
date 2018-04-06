package com.ranran.system.operate.vo;

import com.ranran.core.BaseModel;

import java.io.Serializable;

/*
 * 
 * gen model 2018-01-21
 */
public class TsDictSearchVo extends BaseModel implements Serializable{

    //资源编码
    private String tdResCode;
    //索引
    private String tdKey;
    //类型
    private String tdType;

    public String getTdResCode() {
        return tdResCode;
    }

    public void setTdResCode(String tdResCode) {
        this.tdResCode = tdResCode;
    }

    public String getTdKey() {
        return tdKey;
    }

    public void setTdKey(String tdKey) {
        this.tdKey = tdKey;
    }

    public String getTdType() {
        return tdType;
    }

    public void setTdType(String tdType) {
        this.tdType = tdType;
    }
}
