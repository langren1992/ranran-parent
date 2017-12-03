package com.ranran.test.model;

import com.ranran.jdbc.AbstractModel;
import com.ranran.jdbc.core.BaseModel;
import com.ranran.test.dao.TestTsRoleDao;

import javax.persistence.Id;
import javax.persistence.Table;

public class TestTsRole {

    private Long role_id;

    private String role_no;

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public String getRole_no() {
        return role_no;
    }

    public void setRole_no(String role_no) {
        this.role_no = role_no;
    }
}
