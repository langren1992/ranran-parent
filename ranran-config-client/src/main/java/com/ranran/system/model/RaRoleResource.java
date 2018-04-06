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
@Table(name = "ra_role_resource")
public class RaRoleResource implements Serializable{
	//主键
    @Column(name ="RR_ID")
    @Id
	private Long rrId;
    //角色编码
    @Column(name ="RR_ROLE_NO")
    private String rrRoleNo;
    //资源编码
    @Column(name ="RR_RESOURCE_NO")
    private String rrResourceNo;
    //角色资源描述
    @Column(name ="RR_DESCRIBE")
    private String rrDescribe;
    //资源选择状态
    @Column(name ="CHECKED")
    private Integer checked;
    //资源选择状态
    @Column(name ="CHECK_STATE")
    private String checkState;
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
     * 获取 rrId 主键
     */
    public Long getRrId(){
        return  rrId;
    }
    /**
     * 设置 rrId 主键
     */
    public void setRrId(Long rrId ){
        this.rrId = rrId;
    }

    /**
     * 获取 rrRoleNo 角色编码
     */
    public String getRrRoleNo(){
        return  rrRoleNo;
    }
    /**
     * 设置 rrRoleNo 角色编码
     */
    public void setRrRoleNo(String rrRoleNo ){
        this.rrRoleNo = rrRoleNo;
    }

    /**
     * 获取 rrResourceNo 资源编码
     */
    public String getRrResourceNo(){
        return  rrResourceNo;
    }
    /**
     * 设置 rrResourceNo 资源编码
     */
    public void setRrResourceNo(String rrResourceNo ){
        this.rrResourceNo = rrResourceNo;
    }

    /**
     * 获取 rrDescribe 角色资源描述
     */
    public String getRrDescribe(){
        return  rrDescribe;
    }
    /**
     * 设置 rrDescribe 角色资源描述
     */
    public void setRrDescribe(String rrDescribe ){
        this.rrDescribe = rrDescribe;
    }

    /**
     * 获取 checked 资源选择状态
     */
    public Integer getChecked(){
        return  checked;
    }
    /**
     * 设置 checked 资源选择状态
     */
    public void setChecked(Integer checked ){
        this.checked = checked;
    }

    /**
     * 获取 checkState 资源选择状态
     */
    public String getCheckState(){
        return  checkState;
    }
    /**
     * 设置 checkState 资源选择状态
     */
    public void setCheckState(String checkState ){
        this.checkState = checkState;
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
