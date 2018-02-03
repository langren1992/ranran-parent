package com.ranran.uums.system.operate.vo;

import com.ranran.core.BaseModel;

import javax.persistence.Column;
import java.io.Serializable;

/*
 * 系统控制参数，更新数据视图
 * @creator zengrui 2018-01-30 10:10
 */
public class TsSystemControlUpdateVo implements Serializable{

    private Long tscId;

    private String tscKey;
    private String tscValue;
    private String tscRemark;

    /**
     * 获取 tscId
     */
    public Long getTscId(){
        return  tscId;
    }

    /**
     * 设置 tscId
     */
    public void setTscId(Long tscId ){
        this.tscId = tscId;
    }

    public String getTscKey() {
        return tscKey;
    }

    public void setTscKey(String tscKey) {
        this.tscKey = tscKey;
    }

    public String getTscValue() {
        return tscValue;
    }

    public void setTscValue(String tscValue) {
        this.tscValue = tscValue;
    }

    public String getTscRemark() {
        return tscRemark;
    }

    public void setTscRemark(String tscRemark) {
        this.tscRemark = tscRemark;
    }
}
