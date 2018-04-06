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
@Table(name = "ts_user")
public class TsUser implements Serializable{
    @Column(name ="user_id")
    @Id
	private Long userId;
    //编码/登陆名
    @Column(name ="USER_NO")
    private String userNo;
    //名称
    @Column(name ="USER_NAME")
    private String userName;
    //密码
    @Column(name ="USER_PASSWORD")
    private String userPassword;
    //盐值
    @Column(name ="USER_SALT")
    private String userSalt;
    //状态 0:锁定;1:启用
    @Column(name ="USER_STATUS")
    private Integer userStatus;
    //电话
    @Column(name ="USER_Tel")
    private String userTel;
    //手机
    @Column(name ="USER_PHONE")
    private String userPhone;
    //所属部门编码
    @Column(name ="USER_DEPT_NO")
    private String userDeptNo;
    //所属部门名称
    @Column(name ="USER_DEPT_NAME")
    private String userDeptName;
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
     * 获取 userId 
     */
    public Long getUserId(){
        return  userId;
    }
    /**
     * 设置 userId 
     */
    public void setUserId(Long userId ){
        this.userId = userId;
    }

    /**
     * 获取 userNo 编码/登陆名
     */
    public String getUserNo(){
        return  userNo;
    }
    /**
     * 设置 userNo 编码/登陆名
     */
    public void setUserNo(String userNo ){
        this.userNo = userNo;
    }

    /**
     * 获取 userName 名称
     */
    public String getUserName(){
        return  userName;
    }
    /**
     * 设置 userName 名称
     */
    public void setUserName(String userName ){
        this.userName = userName;
    }

    /**
     * 获取 userPassword 密码
     */
    public String getUserPassword(){
        return  userPassword;
    }
    /**
     * 设置 userPassword 密码
     */
    public void setUserPassword(String userPassword ){
        this.userPassword = userPassword;
    }

    /**
     * 获取 userSalt 盐值
     */
    public String getUserSalt(){
        return  userSalt;
    }
    /**
     * 设置 userSalt 盐值
     */
    public void setUserSalt(String userSalt ){
        this.userSalt = userSalt;
    }

    /**
     * 获取 userStatus 状态 0:锁定;1:启用
     */
    public Integer getUserStatus(){
        return  userStatus;
    }
    /**
     * 设置 userStatus 状态 0:锁定;1:启用
     */
    public void setUserStatus(Integer userStatus ){
        this.userStatus = userStatus;
    }

    /**
     * 获取 userTel 电话
     */
    public String getUserTel(){
        return  userTel;
    }
    /**
     * 设置 userTel 电话
     */
    public void setUserTel(String userTel ){
        this.userTel = userTel;
    }

    /**
     * 获取 userPhone 手机
     */
    public String getUserPhone(){
        return  userPhone;
    }
    /**
     * 设置 userPhone 手机
     */
    public void setUserPhone(String userPhone ){
        this.userPhone = userPhone;
    }

    /**
     * 获取 userDeptNo 所属部门编码
     */
    public String getUserDeptNo(){
        return  userDeptNo;
    }
    /**
     * 设置 userDeptNo 所属部门编码
     */
    public void setUserDeptNo(String userDeptNo ){
        this.userDeptNo = userDeptNo;
    }

    /**
     * 获取 userDeptName 所属部门名称
     */
    public String getUserDeptName(){
        return  userDeptName;
    }
    /**
     * 设置 userDeptName 所属部门名称
     */
    public void setUserDeptName(String userDeptName ){
        this.userDeptName = userDeptName;
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
