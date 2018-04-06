package com.ranran.system.model;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.*;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
/*
 * 
 * gen model 2018-01-20
 */
@Table(name = "ts_resource")
public class TsResource implements Serializable{
	//主键
    @Column(name ="RES_ID")
    @Id
	private Long resId;
    //资源编码
    @Column(name ="RES_NO")
    private String resNo;
    //名称
    @Column(name ="RES_NAME")
    private String resName;
    //父级资源编码
    @Column(name ="RES_PARENT_NO")
    private String resParentNo;
    //父级资源名称
    @Column(name ="RES_PARENT_NAME")
    private String resParentName;
    //路径
    @Column(name ="RES_URL")
    private String resUrl;
    //类型
    @Column(name ="RES_TYPE")
    private Integer resType;
    //状态
    @Column(name ="RES_STATUS")
    private Integer resStatus;
    //权限项
    @Column(name ="RES_PERMISSION")
    private String resPermission;
    //图标
    @Column(name ="RES_ICONCLS")
    private String resIconcls;
    //描述
    @Column(name ="RES_DESCRIBE")
    private String resDescribe;
    //创建人编码
    @Column(name ="CREATOR")
    private String creator;
    //创建时间
    @Column(name ="CREATE_TIME")
    private Timestamp createTime;
    //修改人编码
    @Column(name ="MODIFIER")
    private String modifier;
    //修改时间
    @Column(name ="MODIFY_TIME")
    private Timestamp modifyTime;
    //组织
    @Column(name ="ORG_ID")
    private String orgId;
    //资源版本号
    @Column(name ="REC_VER")
    private Integer recVer;

    /**
     * 获取 resId 主键
     */
    public Long getResId(){
        return  resId;
    }
    /**
     * 设置 resId 主键
     */
    public void setResId(Long resId ){
        this.resId = resId;
    }

    /**
     * 获取 resNo 资源编码
     */
    public String getResNo(){
        return  resNo;
    }
    /**
     * 设置 resNo 资源编码
     */
    public void setResNo(String resNo ){
        this.resNo = resNo;
    }

    /**
     * 获取 resName 名称
     */
    public String getResName(){
        return  resName;
    }
    /**
     * 设置 resName 名称
     */
    public void setResName(String resName ){
        this.resName = resName;
    }

    /**
     * 获取 resParentNo 父级资源编码
     */
    public String getResParentNo(){
        return  resParentNo;
    }
    /**
     * 设置 resParentNo 父级资源编码
     */
    public void setResParentNo(String resParentNo ){
        this.resParentNo = resParentNo;
    }

    /**
     * 获取 resParentName 父级资源名称
     */
    public String getResParentName(){
        return  resParentName;
    }
    /**
     * 设置 resParentName 父级资源名称
     */
    public void setResParentName(String resParentName ){
        this.resParentName = resParentName;
    }

    /**
     * 获取 resUrl 路径
     */
    public String getResUrl(){
        return  resUrl;
    }
    /**
     * 设置 resUrl 路径
     */
    public void setResUrl(String resUrl ){
        this.resUrl = resUrl;
    }

    /**
     * 获取 resType 类型
     */
    public Integer getResType(){
        return  resType;
    }
    /**
     * 设置 resType 类型
     */
    public void setResType(Integer resType ){
        this.resType = resType;
    }

    /**
     * 获取 resStatus 状态
     */
    public Integer getResStatus(){
        return  resStatus;
    }
    /**
     * 设置 resStatus 状态
     */
    public void setResStatus(Integer resStatus ){
        this.resStatus = resStatus;
    }

    /**
     * 获取 resPermission 权限项
     */
    public String getResPermission(){
        return  resPermission;
    }
    /**
     * 设置 resPermission 权限项
     */
    public void setResPermission(String resPermission ){
        this.resPermission = resPermission;
    }

    /**
     * 获取 resIconcls 图标
     */
    public String getResIconcls(){
        return  resIconcls;
    }
    /**
     * 设置 resIconcls 图标
     */
    public void setResIconcls(String resIconcls ){
        this.resIconcls = resIconcls;
    }

    /**
     * 获取 resDescribe 描述
     */
    public String getResDescribe(){
        return  resDescribe;
    }
    /**
     * 设置 resDescribe 描述
     */
    public void setResDescribe(String resDescribe ){
        this.resDescribe = resDescribe;
    }

    /**
     * 获取 creator 创建人编码
     */
    public String getCreator(){
        return  creator;
    }
    /**
     * 设置 creator 创建人编码
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
     * 获取 modifier 修改人编码
     */
    public String getModifier(){
        return  modifier;
    }
    /**
     * 设置 modifier 修改人编码
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
     * 获取 orgId 组织
     */
    public String getOrgId(){
        return  orgId;
    }
    /**
     * 设置 orgId 组织
     */
    public void setOrgId(String orgId ){
        this.orgId = orgId;
    }

    /**
     * 获取 recVer 资源版本号
     */
    public Integer getRecVer(){
        return  recVer;
    }
    /**
     * 设置 recVer 资源版本号
     */
    public void setRecVer(Integer recVer ){
        this.recVer = recVer;
    }

}
