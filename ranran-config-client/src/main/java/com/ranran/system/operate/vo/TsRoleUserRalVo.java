package com.ranran.system.operate.vo;

import com.ranran.system.model.TsUser;

import java.util.ArrayList;
import java.util.List;

/**
 * 前端提交的视图对象
 */
public class TsRoleUserRalVo {

    private String roleNo;

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
    }

    // 操作类型 新增 add 删除 delete
    private String optType;

    private List<TsUser> users = new ArrayList<TsUser>();

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public List<TsUser> getUsers() {
        return users;
    }

    public void setUsers(List<TsUser> users) {
        this.users = users;
    }
}
