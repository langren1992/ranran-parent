package com.ranran.uums.system.operate.vo;

import com.ranran.core.BaseModel;

import java.io.Serializable;
import java.sql.Timestamp;

/*
 * 
 * gen model 2018-01-21
 */
public class TsDictUpdateVo extends BaseModel implements Serializable{

    //主键
    private Long tdId;
    //编码
    private String tdCode;
    //名称
    private String tdName;
    //索引
    private String tdKey;
    //类型
    private String tdType;
    //父编码
    private String tdParentCode;
    //父名称
    private String tdParentName;
    //资源编码
    private String tdResCode;
    //资源名称
    private String tdResName;
    //创建人
    private String creator;
    //创建时间
    private Timestamp createTime;
    //修改人
    private String modifier;
    //修改时间
    private Timestamp modifyTime;
    //所属部门
    private String orgCode;

    /**
     * 获取 tdId 主键
     */
    public Long getTdId(){
        return  tdId;
    }
    /**
     * 设置 tdId 主键
     */
    public void setTdId(Long tdId ){
        this.tdId = tdId;
    }

    /**
     * 获取 tdCode 编码
     */
    public String getTdCode(){
        return  tdCode;
    }
    /**
     * 设置 tdCode 编码
     */
    public void setTdCode(String tdCode ){
        this.tdCode = tdCode;
    }

    /**
     * 获取 tdName 名称
     */
    public String getTdName(){
        return  tdName;
    }
    /**
     * 设置 tdName 名称
     */
    public void setTdName(String tdName ){
        this.tdName = tdName;
    }

    /**
     * 获取 tdKey 索引
     */
    public String getTdKey(){
        return  tdKey;
    }
    /**
     * 设置 tdKey 索引
     */
    public void setTdKey(String tdKey ){
        this.tdKey = tdKey;
    }

    /**
     * 获取 tdType
     */
    public String getTdType(){
        return  tdType;
    }
    /**
     * 设置 tdType
     */
    public void setTdType(String tdType ){
        this.tdType = tdType;
    }

    /**
     * 获取 tdParentCode 父编码
     */
    public String getTdParentCode(){
        return  tdParentCode;
    }
    /**
     * 设置 tdParentCode 父编码
     */
    public void setTdParentCode(String tdParentCode ){
        this.tdParentCode = tdParentCode;
    }

    /**
     * 获取 tdParentName 父名称
     */
    public String getTdParentName(){
        return  tdParentName;
    }
    /**
     * 设置 tdParentName 父名称
     */
    public void setTdParentName(String tdParentName ){
        this.tdParentName = tdParentName;
    }

    /**
     * 获取 tdResCode 资源编码
     */
    public String getTdResCode(){
        return  tdResCode;
    }
    /**
     * 设置 tdResCode 资源编码
     */
    public void setTdResCode(String tdResCode ){
        this.tdResCode = tdResCode;
    }

    /**
     * 获取 tdResName 资源名称
     */
    public String getTdResName(){
        return  tdResName;
    }
    /**
     * 设置 tdResName 资源名称
     */
    public void setTdResName(String tdResName ){
        this.tdResName = tdResName;
    }

    /**
     * 获取 creator 创建人
     */
    public String getCreator(){
        return  creator;
    }
    /**
     * 设置 creator 创建人
     */
    public void setCreator(String creator ){
        this.creator = creator;
    }

    /**
     * 获取 createTime 创建时间
     */
    public Timestamp getCreateTime(){
        return  createTime;
    }
    /**
     * 设置 createTime 创建时间
     */
    public void setCreateTime(Timestamp createTime ){
        this.createTime = createTime;
    }

    /**
     * 获取 modifier 修改人
     */
    public String getModifier(){
        return  modifier;
    }
    /**
     * 设置 modifier 修改人
     */
    public void setModifier(String modifier ){
        this.modifier = modifier;
    }

    /**
     * 获取 modifyTime 修改时间
     */
    public Timestamp getModifyTime(){
        return  modifyTime;
    }
    /**
     * 设置 modifyTime 修改时间
     */
    public void setModifyTime(Timestamp modifyTime ){
        this.modifyTime = modifyTime;
    }

    /**
     * 获取 orgCode 所属部门
     */
    public String getOrgCode(){
        return  orgCode;
    }
    /**
     * 设置 orgCode 所属部门
     */
    public void setOrgCode(String orgCode ){
        this.orgCode = orgCode;
    }
}
