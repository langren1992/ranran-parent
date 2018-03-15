package com.ranran.uums.system.model;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.*;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
/*
 * 省市区县
 * gen model 2018-02-25
 */
@Table(name = "ts_district")
public class TsDistrict implements Serializable{
	//主键
    @Column(name ="dist_id")
    @Id
	private Long distId;
    //区域编码（区域邮政编码）
    @Column(name ="dist_code")
    private String distCode;
    //区域名称
    @Column(name ="dist_name")
    private String distName;
    //父级区域编码（区域邮政编码）
    @Column(name ="dist_parent_code")
    private String distParentCode;
    //父级区域名称
    @Column(name ="dist_parent_name")
    private String distParentName;
    //区域城市编码
    @Column(name ="dist_citycode")
    private String distCitycode;
    //区域经纬度
    @Column(name ="dist_lonlat")
    private String distLonlat;
    //区域层级
    @Column(name ="dist_level")
    private String distLevel;
    @Column(name ="create_time")
    private Timestamp createTime;
    @Column(name ="creator")
    private String creator;
    @Column(name ="modify_time")
    private Timestamp modifyTime;
    @Column(name ="modifier")
    private String modifier;

    /**
     * 获取 distId 主键
     */
    public Long getDistId(){
        return  distId;
    }
    /**
     * 设置 distId 主键
     */
    public void setDistId(Long distId ){
        this.distId = distId;
    }

    /**
     * 获取 distCode 区域编码（区域邮政编码）
     */
    public String getDistCode(){
        return  distCode;
    }
    /**
     * 设置 distCode 区域编码（区域邮政编码）
     */
    public void setDistCode(String distCode ){
        this.distCode = distCode;
    }

    /**
     * 获取 distName 区域名称
     */
    public String getDistName(){
        return  distName;
    }
    /**
     * 设置 distName 区域名称
     */
    public void setDistName(String distName ){
        this.distName = distName;
    }

    /**
     * 获取 distParentCode 父级区域编码（区域邮政编码）
     */
    public String getDistParentCode(){
        return  distParentCode;
    }
    /**
     * 设置 distParentCode 父级区域编码（区域邮政编码）
     */
    public void setDistParentCode(String distParentCode ){
        this.distParentCode = distParentCode;
    }

    /**
     * 获取 distParentName 父级区域名称
     */
    public String getDistParentName(){
        return  distParentName;
    }
    /**
     * 设置 distParentName 父级区域名称
     */
    public void setDistParentName(String distParentName ){
        this.distParentName = distParentName;
    }

    /**
     * 获取 distCitycode 区域城市编码
     */
    public String getDistCitycode(){
        return  distCitycode;
    }
    /**
     * 设置 distCitycode 区域城市编码
     */
    public void setDistCitycode(String distCitycode ){
        this.distCitycode = distCitycode;
    }

    /**
     * 获取 distLonlat 区域经纬度
     */
    public String getDistLonlat(){
        return  distLonlat;
    }
    /**
     * 设置 distLonlat 区域经纬度
     */
    public void setDistLonlat(String distLonlat ){
        this.distLonlat = distLonlat;
    }

    /**
     * 获取 distLevel 区域层级
     */
    public String getDistLevel(){
        return  distLevel;
    }
    /**
     * 设置 distLevel 区域层级
     */
    public void setDistLevel(String distLevel ){
        this.distLevel = distLevel;
    }

    /**
     * 获取 createTime 
     */
    public Timestamp getCreateTime(){
        return  createTime;
    }
    /**
     * 设置 createTime 
     */
    public void setCreateTime(Timestamp createTime ){
        this.createTime = createTime;
    }

    /**
     * 获取 creator 
     */
    public String getCreator(){
        return  creator;
    }
    /**
     * 设置 creator 
     */
    public void setCreator(String creator ){
        this.creator = creator;
    }

    /**
     * 获取 modifyTime 
     */
    public Timestamp getModifyTime(){
        return  modifyTime;
    }
    /**
     * 设置 modifyTime 
     */
    public void setModifyTime(Timestamp modifyTime ){
        this.modifyTime = modifyTime;
    }

    /**
     * 获取 modifier 
     */
    public String getModifier(){
        return  modifier;
    }
    /**
     * 设置 modifier 
     */
    public void setModifier(String modifier ){
        this.modifier = modifier;
    }

}
