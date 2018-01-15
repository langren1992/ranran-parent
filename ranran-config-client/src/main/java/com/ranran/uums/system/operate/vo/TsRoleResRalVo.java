package com.ranran.uums.system.operate.vo;

import com.ranran.uums.system.model.TsResource;
import com.ranran.uums.system.model.TsRole;

import java.util.ArrayList;
import java.util.List;

/**
 * 前端提交的视图对象
 */
public class TsRoleResRalVo {

    //资源类型 菜单 menu 按钮 button
    private String resType;

    private String roleNo;

    private Integer checked;

    private String checkState;

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
    }

    private List<TsResource> resources = new ArrayList<TsResource>();

    public List<TsResource> getResources() {
        return resources;
    }

    public void setResources(List<TsResource> resources) {
        this.resources = resources;
    }

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
}
