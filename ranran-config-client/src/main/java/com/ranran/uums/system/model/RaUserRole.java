package com.ranran.uums.system.model;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.*;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
/*
 * 角色信息
 * gen model 2018-01-20
 */
@Table(name = "ra_user_role")
public class RaUserRole implements Serializable{
	//主键
    @Column(name ="UR_ID")
    @Id
	private Long urId;
    //用户编码
    @Column(name ="UR_USER_NO")
    private String urUserNo;
    //角色编码
    @Column(name ="UR_ROLE_NO")
    private String urRoleNo;
    //用户角色描述
    @Column(name ="UR_DESCRIBE")
    private String urDescribe;
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
     * 获取 urId 主键
     */
    public Long getUrId(){
        return  urId;
    }
    /**
     * 设置 urId 主键
     */
    public void setUrId(Long urId ){
        this.urId = urId;
    }

    /**
     * 获取 urUserNo 用户编码
     */
    public String getUrUserNo(){
        return  urUserNo;
    }
    /**
     * 设置 urUserNo 用户编码
     */
    public void setUrUserNo(String urUserNo ){
        this.urUserNo = urUserNo;
    }

    /**
     * 获取 urRoleNo 角色编码
     */
    public String getUrRoleNo(){
        return  urRoleNo;
    }
    /**
     * 设置 urRoleNo 角色编码
     */
    public void setUrRoleNo(String urRoleNo ){
        this.urRoleNo = urRoleNo;
    }

    /**
     * 获取 urDescribe 用户角色描述
     */
    public String getUrDescribe(){
        return  urDescribe;
    }
    /**
     * 设置 urDescribe 用户角色描述
     */
    public void setUrDescribe(String urDescribe ){
        this.urDescribe = urDescribe;
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
