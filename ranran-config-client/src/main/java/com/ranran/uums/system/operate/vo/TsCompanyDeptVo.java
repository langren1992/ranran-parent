package com.ranran.uums.system.operate.vo;


import com.ranran.uums.system.model.TsCompany;
import com.ranran.uums.system.model.TsDept;

import java.util.List;

/**
 * 公司下的部门
 *
 * @author 曾睿
 * @create 2017-08-11 9:59
 **/
public class TsCompanyDeptVo extends TsCompany {

    private List<TsDept> deptList;

    public List<TsDept> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<TsDept> deptList) {
        this.deptList = deptList;
    }
}
