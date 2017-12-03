package com.ranran.test.service;



import com.ranran.jdbc.core.JdbcException;
import com.ranran.jdbc.core.SelectCondition;
import com.ranran.test.model.TestTsRole;

import java.util.List;

public interface TsRoleService {
    List<TestTsRole> select(Class<TestTsRole> testTsRoleClass,SelectCondition[] conditionsl) throws JdbcException;

    void  sayHi(String s);
}
