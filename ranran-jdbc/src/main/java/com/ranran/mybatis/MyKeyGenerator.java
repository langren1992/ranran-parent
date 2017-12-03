package com.ranran.mybatis;


import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;

import java.lang.reflect.Method;
import java.sql.Statement;

public class MyKeyGenerator implements KeyGenerator {

    @Override
    public void processBefore(Executor executor, MappedStatement mappedStatement, Statement statement, Object o) {

    }

    @Override
    public void processAfter(Executor executor, MappedStatement mappedStatement, Statement statement, Object o) {

    }
}
