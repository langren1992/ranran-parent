package com.ranran.uums.system.operate.vo;

import java.io.Serializable;
import java.util.Date;

/*
 * 角色表
 * gen model 2017-12-10
 */
public class TsRoleUpdateVo implements Serializable{
	//主键
	private Long roleId;
    //角色编码
    private String roleNo;
    //名称
    private String roleName;
    //状态
    private Integer roleStatus;
    //角色描述
    private String roleDescribe;
    //创建人编码
    private String creator;
    //创建时间
    private Date createTime;
    //修改人编码
    private String modifier;
    //修改时间
    private Date modifyTime;
    //组织
    private String orgId;
    //资源版本号
    private Integer recVer;

    /**
     * 获取 roleId 主键
     */
    public Long getRoleId(){
        return  roleId;
    }
    /**
     * 设置 roleId 主键
     */
    public void setRoleId(Long roleId ){
        this.roleId = roleId;
    }

    /**
     * 获取 roleNo 角色编码
     */
    public String getRoleNo(){
        return  roleNo;
    }
    /**
     * 设置 roleNo 角色编码
     */
    public void setRoleNo(String roleNo ){
        this.roleNo = roleNo;
    }

    /**
     * 获取 roleName 名称
     */
    public String getRoleName(){
        return  roleName;
    }
    /**
     * 设置 roleName 名称
     */
    public void setRoleName(String roleName ){
        this.roleName = roleName;
    }

    /**
     * 获取 roleStatus 状态
     */
    public Integer getRoleStatus(){
        return  roleStatus;
    }
    /**
     * 设置 roleStatus 状态
     */
    public void setRoleStatus(Integer roleStatus ){
        this.roleStatus = roleStatus;
    }

    /**
     * 获取 roleDescribe 角色描述
     */
    public String getRoleDescribe(){
        return  roleDescribe;
    }
    /**
     * 设置 roleDescribe 角色描述
     */
    public void setRoleDescribe(String roleDescribe ){
        this.roleDescribe = roleDescribe;
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
    public Date getCreateTime(){
        return  createTime;
    }
    /**
     * 设置 createTime 创建时间
     */
    public void setCreateTime(Date createTime ){
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
    public Date getModifyTime(){
        return  modifyTime;
    }
    /**
     * 设置 modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime ){
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
