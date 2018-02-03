package com.ranran.uums.system.model;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.*;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
/*
 * 
 * gen model 2018-02-03
 */
@Table(name = "ts_district")
public class TsDistrict implements Serializable{
    @Column(name ="dist_id")
    @Id
	private Long distId;
    @Column(name ="dist_code")
    private Long distCode;
    @Column(name ="dist_name")
    private String distName;
    @Column(name ="dist_parent_code")
    private Long distParentCode;
    @Column(name ="dist_citycode")
    private String distCitycode;
    @Column(name ="dist_adcode")
    private String distAdcode;
    @Column(name ="dist_lon")
    private Double distLon;
    @Column(name ="dist_lat")
    private Double distLat;
    @Column(name ="dist_level")
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
