package com.ranran.uums.system.operate.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.ranran.core.BaseModel;

import java.io.Serializable;

/*
 * 
 * @creator zengrui 2018-02-03 05:27
 */
public class TsDistrictSyncMapVo  implements Serializable{


    private String distName;
    private String distLevel;

    public String getDistName() {
        return distName;
    }

    public void setDistName(String distName) {
        this.distName = distName;
    }

    /**
     * 获取 distLevel 
     */
    public String getDistLevel(){
        return  distLevel;
    }
    /**
     * 设置 distLevel 
     */
    public void setDistLevel(String distLevel ){
        this.distLevel = distLevel;
    }

}
