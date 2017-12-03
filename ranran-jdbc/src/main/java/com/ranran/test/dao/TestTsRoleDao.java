package com.ranran.test.dao;

import com.ranran.test.model.TestTsRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestTsRoleDao {


    void insert(TestTsRole testTsRole);

}
