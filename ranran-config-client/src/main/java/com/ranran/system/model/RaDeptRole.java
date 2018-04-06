package com.ranran.system.model;
import java.sql.Timestamp;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
/*
 * 
 * gen model 2018-01-20
 */
@Table(name = "ra_dept_role")
public class RaDeptRole implements Serializable{
	//主键
    @Column(name ="DR_ID")
    @Id
	private String drId;
    //部门编码
    @Column(name ="DR_DEPT_NO")
    private String drDeptNo;
    //角色编码
    @Column(name ="DR_ROLE_NO")
    private String drRoleNo;
    //部门角色描述
    @Column(name ="DR_DESCRIBE")
    private String drDescribe;
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
     * 获取 drId 主键
     */
    public String getDrId(){
        return  drId;
    }
    /**
     * 设置 drId 主键
     */
    public void setDrId(String drId ){
        this.drId = drId;
    }

    /**
     * 获取 drDeptNo 部门编码
     */
    public String getDrDeptNo(){
        return  drDeptNo;
    }
    /**
     * 设置 drDeptNo 部门编码
     */
    public void setDrDeptNo(String drDeptNo ){
        this.drDeptNo = drDeptNo;
    }

    /**
     * 获取 drRoleNo 角色编码
     */
    public String getDrRoleNo(){
        return  drRoleNo;
    }
    /**
     * 设置 drRoleNo 角色编码
     */
    public void setDrRoleNo(String drRoleNo ){
        this.drRoleNo = drRoleNo;
    }

    /**
     * 获取 drDescribe 部门角色描述
     */
    public String getDrDescribe(){
        return  drDescribe;
    }
    /**
     * 设置 drDescribe 部门角色描述
     */
    public void setDrDescribe(String drDescribe ){
        this.drDescribe = drDescribe;
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
