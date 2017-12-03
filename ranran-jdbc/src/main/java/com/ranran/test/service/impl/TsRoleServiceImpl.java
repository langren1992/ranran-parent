package com.ranran.test.service.impl;

import com.ranran.jdbc.AbstractModel;
import com.ranran.jdbc.core.JdbcException;
import com.ranran.jdbc.core.SelectCondition;
import com.ranran.test.dao.TestTsRoleDao;
import com.ranran.test.model.TestTsRole;
import com.ranran.test.service.TsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TsRoleServiceImpl implements TsRoleService {

    @Override
    public List<TestTsRole> select(Class<TestTsRole> testTsRoleClass, SelectCondition[] conditionsl) throws JdbcException {
        testTsRoleDao.insert(new TestTsRole());
        return null;
    }

    @Override
    public void sayHi(String s) {

    }

    @Autowired
    TestTsRoleDao testTsRoleDao;
//
//    public List<TestTsRole> select(Class<TestTsRole> testTsRoleClass,SelectCondition[] conditions) throws JdbcException {
////        testTsRoleDao.selectById(testTsRoleClass,1);
////        testTsRoleDao.selectAll(testTsRoleClass);
//        return testTsRoleDao.selectByCondition(testTsRoleClass,conditions);
//    }
//
//    public void  sayHi(String s){
//        System.out.println("执行中"+s);
//    }
}
