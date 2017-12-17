package com.ranran.uums.system.operate.vo;


import com.ranran.uums.system.model.TsResource;

/**
 * 前台角色资源显示数据
 */
public class TsRoleResourceVo extends TsResource {

    private String roleNo;
    //资源选择状态
    private Integer checked;
    //资源选择状态
    private String checkState;

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    public String getCheckState() {
        return checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState;
    }

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
    }
}
