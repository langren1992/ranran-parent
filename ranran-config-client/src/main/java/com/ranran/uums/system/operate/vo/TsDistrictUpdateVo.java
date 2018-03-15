package com.ranran.uums.system.operate.vo;

import com.ranran.core.BaseModel;

import java.io.Serializable;
/*
 * 
 * @creator zengrui 2018-02-03 05:27
 */
public class TsDistrictUpdateVo implements Serializable{

    private Long distId;
    private String distCode;
    private String distName;
    private String distParentCode;
    private String distParentName;
    private String distLevel;

    public Long getDistId() {
        return distId;
    }

    public void setDistId(Long distId) {
        this.distId = distId;
    }

    public String getDistCode() {
        return distCode;
    }

    public void setDistCode(String distCode) {
        this.distCode = distCode;
    }

    public String getDistName() {
        return distName;
    }

    public void setDistName(String distName) {
        this.distName = distName;
    }

    public String getDistParentCode() {
        return distParentCode;
    }

    public void setDistParentCode(String distParentCode) {
        this.distParentCode = distParentCode;
    }

    public String getDistParentName() {
        return distParentName;
    }

    public void setDistParentName(String distParentName) {
        this.distParentName = distParentName;
    }

    public String getDistLevel() {
        return distLevel;
    }

    public void setDistLevel(String distLevel) {
        this.distLevel = distLevel;
    }



}
