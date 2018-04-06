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
@Table(name = "ra_user_dept")
public class RaUserDept implements Serializable{
	//主键
    @Column(name ="UD_ID")
    @Id
	private Long udId;
    //用户编码
    @Column(name ="UD_USER_NO")
    private String udUserNo;
    //部门编码
    @Column(name ="UD_DEPT_NO")
    private String udDeptNo;
    //部门角色描述
    @Column(name ="UD_DESCRIBE")
    private String udDescribe;
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
     * 获取 udId 主键
     */
    public Long getUdId(){
        return  udId;
    }
    /**
     * 设置 udId 主键
     */
    public void setUdId(Long udId ){
        this.udId = udId;
    }

    /**
     * 获取 udUserNo 用户编码
     */
    public String getUdUserNo(){
        return  udUserNo;
    }
    /**
     * 设置 udUserNo 用户编码
     */
    public void setUdUserNo(String udUserNo ){
        this.udUserNo = udUserNo;
    }

    /**
     * 获取 udDeptNo 部门编码
     */
    public String getUdDeptNo(){
        return  udDeptNo;
    }
    /**
     * 设置 udDeptNo 部门编码
     */
    public void setUdDeptNo(String udDeptNo ){
        this.udDeptNo = udDeptNo;
    }

    /**
     * 获取 udDescribe 部门角色描述
     */
    public String getUdDescribe(){
        return  udDescribe;
    }
    /**
     * 设置 udDescribe 部门角色描述
     */
    public void setUdDescribe(String udDescribe ){
        this.udDescribe = udDescribe;
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
