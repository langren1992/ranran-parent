package com.ranran.system.operate.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.ranran.core.BaseModel;

import java.io.Serializable;

/*
 * 系统控制参数，查询视图
 * @creator zengrui 2018-01-30 10:10
 */
public class TsSystemControlImportVo implements Serializable{

    @Excel(name = "控制键", orderNum = "0")
    private String tscKey;
    @Excel(name = "控制值", orderNum = "1")
    private String tscValue;
    @Excel(name = "备注", orderNum = "2")
    private String tscRemark;
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

    public String getTscRemark() {
        return tscRemark;
    }

    public void setTscRemark(String tscRemark) {
        this.tscRemark = tscRemark;
    }

}
