package com.ranran.system.model;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.*;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
/*
 * 系统控制参数
 * gen model 2018-01-30
 */
@Table(name = "ts_system_control")
public class TsSystemControl implements Serializable{
    @Column(name ="tsc_id")
    @Id
	private Long tscId;
    @Column(name ="tsc_key")
    private String tscKey;
    @Column(name ="tsc_value")
    private String tscValue;
    @Column(name ="tsc_remark")
    private String tscRemark;
    @Column(name ="creator")
    private String creator;
    @Column(name ="create_time")
    private Timestamp createTime;
    @Column(name ="modifier")
    private String modifier;
    @Column(name ="modify_time")
    private Timestamp modifyTime;
    @Column(name ="org_code")
    private String orgCode;

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

    /**
     * 获取 tscRemark 
     */
    public String getTscRemark(){
        return  tscRemark;
    }
    /**
     * 设置 tscRemark 
     */
    public void setTscRemark(String tscRemark ){
        this.tscRemark = tscRemark;
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
     * 获取 orgCode 
     */
    public String getOrgCode(){
        return  orgCode;
    }
    /**
     * 设置 orgCode 
     */
    public void setOrgCode(String orgCode ){
        this.orgCode = orgCode;
    }

}
