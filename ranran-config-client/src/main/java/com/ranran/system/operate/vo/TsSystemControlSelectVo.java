package com.ranran.system.operate.vo;

import com.ranran.core.BaseModel;

import java.io.Serializable;
/*
 * 系统控制参数，查询视图
 * @creator zengrui 2018-01-30 10:10
 */
public class TsSystemControlSelectVo extends BaseModel implements Serializable{

    private String tscKey;
    private String tscValue;
    /**
     * 获取 tscKey
     */
    public String getTscKey(){
        return  tscKey;
    }
    /**
     * 设置 tscKey
     */
    public void setTscKey(String tscKey ){
        this.tscKey = tscKey;
    }

    /**
     * 获取 tscValue
     */
    public String getTscValue(){
        return  tscValue;
    }
    /**
     * 设置 tscValue
     */
    public void setTscValue(String tscValue ){
        this.tscValue = tscValue;
    }

}
