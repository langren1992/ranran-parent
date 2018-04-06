package com.ranran.system.operate.vo;

import java.io.Serializable;

/*
 * 系统控制参数，更新数据视图
 * @creator zengrui 2018-01-30 10:10
 */
public class TsSystemControlDeleteVo implements Serializable{

    private Long tscId;

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
}
