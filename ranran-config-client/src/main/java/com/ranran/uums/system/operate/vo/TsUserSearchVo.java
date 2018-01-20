package com.ranran.uums.system.operate.vo;

import com.ranran.core.BaseModel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/*
 * 用户查询条件视图
 * gen model 2018-01-20
 */
public class TsUserSearchVo extends BaseModel implements Serializable{

    private String userDeptNo;

    public String getUserDeptNo() {
        return userDeptNo;
    }

    public void setUserDeptNo(String userDeptNo) {
        this.userDeptNo = userDeptNo;
    }
}
