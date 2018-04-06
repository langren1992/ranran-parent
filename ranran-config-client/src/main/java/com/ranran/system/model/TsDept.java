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
@Table(name = "ts_dept")
public class TsDept implements Serializable{
	//主键
    @Column(name ="DEPT_ID")
    @Id
	private Long deptId;
    //编码
    @Column(name ="DEPT_NO")
    private String deptNo;
    //名称
    @Column(name ="DEPT_NAME")
    private String deptName;
    //父级编码
    @Column(name ="DEPT_PARENT_NO")
    private String deptParentNo;
    //父级名称
    @Column(name ="DEPT_PARENT_NAME_CN")
    private String deptParentNameCn;
    //级别
    @Column(name ="DEPT_LEVEL")
    private Integer deptLevel;
    //状态
    @Column(name ="DEPT_STATUS")
    private Integer deptStatus;
    //负责人编码
    @Column(name ="DEPT_LEAD_NO")
    private String deptLeadNo;
    //负责人名称
    @Column(name ="DEPT_LEAD_NAME")
    private String deptLeadName;
    //电话
    @Column(name ="DEPT_TEL")
    private String deptTel;
    //服务区域编码
    @Column(name ="DEPT_AREA_NO")
    private String deptAreaNo;
    //服务区名称
    @Column(name ="DEPT_AREA_NAME")
    private String deptAreaName;
    //上级服务区域编码
    @Column(name ="DEPT_PRO_AREA_NO")
    private String deptProAreaNo;
    //上级服务区域名称
    @Column(name ="DEPT_PRO_AREA_NAME")
    private String deptProAreaName;
    //所在省
    @Column(name ="DEPT_PROVINCE")
    private String deptProvince;
    //所在市
    @Column(name ="DEPT_CITY")
    private String deptCity;
    //所在区/县
    @Column(name ="DEPT_DISTRICT")
    private String deptDistrict;
    //详细地址
    @Column(name ="DEPT_ADDRESS")
    private String deptAddress;
    //经度
    @Column(name ="DEPT_LON")
    private Double deptLon;
    //经度
    @Column(name ="DEPT_LAT")
    private Double deptLat;
    //是否与上级服务区域合并区域
    @Column(name ="DEPT_MEG_AREA")
    private Integer deptMegArea;
    //保价费率
    @Column(name ="DEPT_INSUR_RATE")
    private Double deptInsurRate;
    //是否虚拟组织
    @Column(name ="DEPT_VIRTUAL")
    private Integer deptVirtual;
    //代收货款费率
    @Column(name ="DEPT_COLLECT_RATE")
    private Double deptCollectRate;
    //临欠额度
    @Column(name ="DEPT_DEBT_AMOUNT")
    private Double deptDebtAmount;
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
     * 获取 deptId 主键
     */
    public Long getDeptId(){
        return  deptId;
    }
    /**
     * 设置 deptId 主键
     */
    public void setDeptId(Long deptId ){
        this.deptId = deptId;
    }

    /**
     * 获取 deptNo 编码
     */
    public String getDeptNo(){
        return  deptNo;
    }
    /**
     * 设置 deptNo 编码
     */
    public void setDeptNo(String deptNo ){
        this.deptNo = deptNo;
    }

    /**
     * 获取 deptName 名称
     */
    public String getDeptName(){
        return  deptName;
    }
    /**
     * 设置 deptName 名称
     */
    public void setDeptName(String deptName ){
        this.deptName = deptName;
    }

    /**
     * 获取 deptParentNo 父级编码
     */
    public String getDeptParentNo(){
        return  deptParentNo;
    }
    /**
     * 设置 deptParentNo 父级编码
     */
    public void setDeptParentNo(String deptParentNo ){
        this.deptParentNo = deptParentNo;
    }

    /**
     * 获取 deptParentNameCn 父级名称
     */
    public String getDeptParentNameCn(){
        return  deptParentNameCn;
    }
    /**
     * 设置 deptParentNameCn 父级名称
     */
    public void setDeptParentNameCn(String deptParentNameCn ){
        this.deptParentNameCn = deptParentNameCn;
    }

    /**
     * 获取 deptLever 级别
     */
    public Integer getDeptLevel(){
        return  deptLevel;
    }
    /**
     * 设置 deptLever 级别
     */
    public void setDeptLevel(Integer deptLevel ){
        this.deptLevel = deptLevel;
    }

    /**
     * 获取 deptStatus 状态
     */
    public Integer getDeptStatus(){
        return  deptStatus;
    }
    /**
     * 设置 deptStatus 状态
     */
    public void setDeptStatus(Integer deptStatus ){
        this.deptStatus = deptStatus;
    }

    /**
     * 获取 deptLeadNo 负责人编码
     */
    public String getDeptLeadNo(){
        return  deptLeadNo;
    }
    /**
     * 设置 deptLeadNo 负责人编码
     */
    public void setDeptLeadNo(String deptLeadNo ){
        this.deptLeadNo = deptLeadNo;
    }

    /**
     * 获取 deptLeadName 负责人名称
     */
    public String getDeptLeadName(){
        return  deptLeadName;
    }
    /**
     * 设置 deptLeadName 负责人名称
     */
    public void setDeptLeadName(String deptLeadName ){
        this.deptLeadName = deptLeadName;
    }

    /**
     * 获取 deptTel 电话
     */
    public String getDeptTel(){
        return  deptTel;
    }
    /**
     * 设置 deptTel 电话
     */
    public void setDeptTel(String deptTel ){
        this.deptTel = deptTel;
    }

    /**
     * 获取 deptAreaNo 服务区域编码
     */
    public String getDeptAreaNo(){
        return  deptAreaNo;
    }
    /**
     * 设置 deptAreaNo 服务区域编码
     */
    public void setDeptAreaNo(String deptAreaNo ){
        this.deptAreaNo = deptAreaNo;
    }

    /**
     * 获取 deptAreaName 服务区名称
     */
    public String getDeptAreaName(){
        return  deptAreaName;
    }
    /**
     * 设置 deptAreaName 服务区名称
     */
    public void setDeptAreaName(String deptAreaName ){
        this.deptAreaName = deptAreaName;
    }

    /**
     * 获取 deptProAreaNo 上级服务区域编码
     */
    public String getDeptProAreaNo(){
        return  deptProAreaNo;
    }
    /**
     * 设置 deptProAreaNo 上级服务区域编码
     */
    public void setDeptProAreaNo(String deptProAreaNo ){
        this.deptProAreaNo = deptProAreaNo;
    }

    /**
     * 获取 deptProAreaName 上级服务区域名称
     */
    public String getDeptProAreaName(){
        return  deptProAreaName;
    }
    /**
     * 设置 deptProAreaName 上级服务区域名称
     */
    public void setDeptProAreaName(String deptProAreaName ){
        this.deptProAreaName = deptProAreaName;
    }

    /**
     * 获取 deptProvince 所在省
     */
    public String getDeptProvince(){
        return  deptProvince;
    }
    /**
     * 设置 deptProvince 所在省
     */
    public void setDeptProvince(String deptProvince ){
        this.deptProvince = deptProvince;
    }

    /**
     * 获取 deptCity 所在市
     */
    public String getDeptCity(){
        return  deptCity;
    }
    /**
     * 设置 deptCity 所在市
     */
    public void setDeptCity(String deptCity ){
        this.deptCity = deptCity;
    }

    /**
     * 获取 deptDistrict 所在区/县
     */
    public String getDeptDistrict(){
        return  deptDistrict;
    }
    /**
     * 设置 deptDistrict 所在区/县
     */
    public void setDeptDistrict(String deptDistrict ){
        this.deptDistrict = deptDistrict;
    }

    /**
     * 获取 deptAddress 详细地址
     */
    public String getDeptAddress(){
        return  deptAddress;
    }
    /**
     * 设置 deptAddress 详细地址
     */
    public void setDeptAddress(String deptAddress ){
        this.deptAddress = deptAddress;
    }

    /**
     * 获取 deptLon 经度
     */
    public Double getDeptLon(){
        return  deptLon;
    }
    /**
     * 设置 deptLon 经度
     */
    public void setDeptLon(Double deptLon ){
        this.deptLon = deptLon;
    }

    /**
     * 获取 deptLat 经度
     */
    public Double getDeptLat(){
        return  deptLat;
    }
    /**
     * 设置 deptLat 经度
     */
    public void setDeptLat(Double deptLat ){
        this.deptLat = deptLat;
    }

    /**
     * 获取 deptMegArea 是否与上级服务区域合并区域
     */
    public Integer getDeptMegArea(){
        return  deptMegArea;
    }
    /**
     * 设置 deptMegArea 是否与上级服务区域合并区域
     */
    public void setDeptMegArea(Integer deptMegArea ){
        this.deptMegArea = deptMegArea;
    }

    /**
     * 获取 deptInsurRate 保价费率
     */
    public Double getDeptInsurRate(){
        return  deptInsurRate;
    }
    /**
     * 设置 deptInsurRate 保价费率
     */
    public void setDeptInsurRate(Double deptInsurRate ){
        this.deptInsurRate = deptInsurRate;
    }

    /**
     * 获取 deptVirtual 是否虚拟组织
     */
    public Integer getDeptVirtual(){
        return  deptVirtual;
    }
    /**
     * 设置 deptVirtual 是否虚拟组织
     */
    public void setDeptVirtual(Integer deptVirtual ){
        this.deptVirtual = deptVirtual;
    }

    /**
     * 获取 deptCollectRate 代收货款费率
     */
    public Double getDeptCollectRate(){
        return  deptCollectRate;
    }
    /**
     * 设置 deptCollectRate 代收货款费率
     */
    public void setDeptCollectRate(Double deptCollectRate ){
        this.deptCollectRate = deptCollectRate;
    }

    /**
     * 获取 deptDebtAmount 临欠额度
     */
    public Double getDeptDebtAmount(){
        return  deptDebtAmount;
    }
    /**
     * 设置 deptDebtAmount 临欠额度
     */
    public void setDeptDebtAmount(Double deptDebtAmount ){
        this.deptDebtAmount = deptDebtAmount;
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
