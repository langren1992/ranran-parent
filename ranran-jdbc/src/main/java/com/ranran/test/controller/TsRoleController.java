package com.ranran.test.controller;

import com.ranran.jdbc.core.ConditionOperateType;
import com.ranran.jdbc.core.JdbcException;
import com.ranran.jdbc.core.SelectCondition;
import com.ranran.test.model.TestTsRole;
import com.ranran.test.service.TsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tsRole")
public class TsRoleController {

    @Autowired
    private TsRoleService tsRoleService;

    @RequestMapping("/get")
    public List<TestTsRole> select(){
        TestTsRole testTsRole = new TestTsRole();
        SelectCondition selectCondition = new SelectCondition();
        selectCondition.setFieldName("role_Id");
        selectCondition.setValues(new Object[]{1,2});
        selectCondition.setOperator(ConditionOperateType.IN);
        try {
            return tsRoleService.select(TestTsRole.class,new SelectCondition[]{selectCondition});
        } catch (JdbcException e) {
            e.printStackTrace();
        }
        return null;
    }
}