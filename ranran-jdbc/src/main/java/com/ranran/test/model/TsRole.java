//package com.ranran.test.model;
//
//import com.ranran.core.BaseModel;
//import org.beetl.sql.core.annotatoin.AssignID;
//
//import javax.persistence.Column;
//import javax.persistence.Table;
//import java.io.Serializable;
//import java.sql.Timestamp;
//
///*
// * 角色表
// * gen model 2017-11-24
// */
//@Table(name = "ts_role")
//public class TsRole implements BaseModel,Serializable{
//	//主键
//    @AssignID("simple")
//    @Column(name ="ROLE_ID")
//	private Long roleId;
//    //角色编码
//    @Column(name ="ROLE_NO")
//    private String roleNo;
//    //名称
//    @Column(name ="ROLE_NAME")
//    private String roleName;
//    //状态
//    @Column(name ="ROLE_STATUS")
//    private Integer roleStatus;
//    //角色描述
//    @Column(name ="ROLE_DESCRIBE")
//    private String roleDescribe;
//    //创建人编码
//    @Column(name ="CREATOR")
//    private String creator;
//    //创建时间
//    @Column(name ="CREATE_TIME")
//    private Timestamp createTime;
//    //修改人编码
//    @Column(name ="MODIFIER")
//    private String modifier;
//    //修改时间
//    @Column(name ="MODIFY_TIME")
//    private Timestamp modifyTime;
//    //组织
//    @Column(name ="ORG_ID")
//    private String orgId;
//    //资源版本号
//    @Column(name ="REC_VER")
//    private Integer recVer;
//
//	public TsRole() {
//	}
//
//    @Override
//    public Object[] getPrimaryKey() {
//        return new Object[]{roleId};
//    }
//
//    /**
//     * 获取 roleId 主键
//     */
//    public Long getRoleId(){
//        return  roleId;
//    }
//    /**
//     * 设置 roleId 主键
//     */
//    public void setRoleId(Long roleId ){
//        this.roleId = roleId;
//    }
//
//    /**
//     * 获取 roleNo 角色编码
//     */
//    public String getRoleNo(){
//        return  roleNo;
//    }
//    /**
//     * 设置 roleNo 角色编码
//     */
//    public void setRoleNo(String roleNo ){
//        this.roleNo = roleNo;
//    }
//
//    /**
//     * 获取 roleName 名称
//     */
//    public String getRoleName(){
//        return  roleName;
//    }
//    /**
//     * 设置 roleName 名称
//     */
//    public void setRoleName(String roleName ){
//        this.roleName = roleName;
//    }
//
//    /**
//     * 获取 roleStatus 状态
//     */
//    public Integer getRoleStatus(){
//        return  roleStatus;
//    }
//    /**
//     * 设置 roleStatus 状态
//     */
//    public void setRoleStatus(Integer roleStatus ){
//        this.roleStatus = roleStatus;
//    }
//
//    /**
//     * 获取 roleDescribe 角色描述
//     */
//    public String getRoleDescribe(){
//        return  roleDescribe;
//    }
//    /**
//     * 设置 roleDescribe 角色描述
//     */
//    public void setRoleDescribe(String roleDescribe ){
//        this.roleDescribe = roleDescribe;
//    }
//
//    /**
//     * 获取 creator 创建人编码
//     */
//    public String getCreator(){
//        return  creator;
//    }
//    /**
//     * 设置 creator 创建人编码
//     */
//    public void setCreator(String creator ){
//        this.creator = creator;
//    }
//
//    /**
//     * 获取 createTime 创建时间
//     */
//    public Timestamp getCreateTime(){
//        return  createTime;
//    }
//    /**
//     * 设置 createTime 创建时间
//     */
//    public void setCreateTime(Timestamp createTime ){
//        this.createTime = createTime;
//    }
//
//    /**
//     * 获取 modifier 修改人编码
//     */
//    public String getModifier(){
//        return  modifier;
//    }
//    /**
//     * 设置 modifier 修改人编码
//     */
//    public void setModifier(String modifier ){
//        this.modifier = modifier;
//    }
//
//    /**
//     * 获取 modifyTime 修改时间
//     */
//    public Timestamp getModifyTime(){
//        return  modifyTime;
//    }
//    /**
//     * 设置 modifyTime 修改时间
//     */
//    public void setModifyTime(Timestamp modifyTime ){
//        this.modifyTime = modifyTime;
//    }
//
//    /**
//     * 获取 orgId 组织
//     */
//    public String getOrgId(){
//        return  orgId;
//    }
//    /**
//     * 设置 orgId 组织
//     */
//    public void setOrgId(String orgId ){
//        this.orgId = orgId;
//    }
//
//    /**
//     * 获取 recVer 资源版本号
//     */
//    public Integer getRecVer(){
//        return  recVer;
//    }
//    /**
//     * 设置 recVer 资源版本号
//     */
//    public void setRecVer(Integer recVer ){
//        this.recVer = recVer;
//    }
//
//}
