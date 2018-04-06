package com.ranran.system.operate.vo;

import com.ranran.system.model.TsRole;

import java.util.ArrayList;
import java.util.List;

/**
 * 前端提交的视图对象
 */
public class TsRoleBatchVo {

    private List<TsRole> roles = new ArrayList<TsRole>();

    public List<TsRole> getRoles() {
        return roles;
    }

    public void setRoles(List<TsRole> roles) {
        this.roles = roles;
    }
}
