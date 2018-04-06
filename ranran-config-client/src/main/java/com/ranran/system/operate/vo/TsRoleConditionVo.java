package com.ranran.system.operate.vo;

import com.ranran.core.BaseConditionVo;

/**
 * 前台的查询条件对象
 */
public class TsRoleConditionVo extends BaseConditionVo{

    private String roleNo;

    private String roleName;

    private String roleStatus;

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }
}
