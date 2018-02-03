package com.ranran.uums.system.operate.vo;

import com.ranran.core.BaseModel;

import java.io.Serializable;
/*
 * 
 * @creator zengrui 2018-02-03 05:27
 */
public class TsDistrictSelectVo extends BaseModel implements Serializable{

    private Long distId;
    private Long distCode;
    private String distName;
    private Long distParentCode;
    private String distCitycode;
    private String distAdcode;
    private Double distLon;
    private Double distLat;
    private String distLevel;

    /**
     * 获取 distId 
     */
    public Long getDistId(){
        return  distId;
    }
    /**
     * 设置 distId 
     */
    public void setDistId(Long distId ){
        this.distId = distId;
    }

    /**
     * 获取 distCode 
     */
    public Long getDistCode(){
        return  distCode;
    }
    /**
     * 设置 distCode 
     */
    public void setDistCode(Long distCode ){
        this.distCode = distCode;
    }

    /**
     * 获取 distName 
     */
    public String getDistName(){
        return  distName;
    }
    /**
     * 设置 distName 
     */
    public void setDistName(String distName ){
        this.distName = distName;
    }

    /**
     * 获取 distParentCode 
     */
    public Long getDistParentCode(){
        return  distParentCode;
    }
    /**
     * 设置 distParentCode 
     */
    public void setDistParentCode(Long distParentCode ){
        this.distParentCode = distParentCode;
    }

    /**
     * 获取 distCitycode 
     */
    public String getDistCitycode(){
        return  distCitycode;
    }
    /**
     * 设置 distCitycode 
     */
    public void setDistCitycode(String distCitycode ){
        this.distCitycode = distCitycode;
    }

    /**
     * 获取 distAdcode 
     */
    public String getDistAdcode(){
        return  distAdcode;
    }
    /**
     * 设置 distAdcode 
     */
    public void setDistAdcode(String distAdcode ){
        this.distAdcode = distAdcode;
    }

    /**
     * 获取 distLon 
     */
    public Double getDistLon(){
        return  distLon;
    }
    /**
     * 设置 distLon 
     */
    public void setDistLon(Double distLon ){
        this.distLon = distLon;
    }

    /**
     * 获取 distLat 
     */
    public Double getDistLat(){
        return  distLat;
    }
    /**
     * 设置 distLat 
     */
    public void setDistLat(Double distLat ){
        this.distLat = distLat;
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
