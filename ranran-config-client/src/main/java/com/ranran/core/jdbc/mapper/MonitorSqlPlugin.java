package com.ranran.core.jdbc.mapper;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * 因为有 http://localhost:8080/druid/index.html 暂时不用
 * Created by zengrui on 2017/5/14.
 */
@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
@Component
    public class MonitorSqlPlugin implements Interceptor {

    private Logger logger = LoggerFactory.getLogger(MonitorSqlPlugin.class);

    /**
     * 监控SQL执行时间
     * */
    public Object intercept(Invocation invocation) throws Throwable {
        long t1 = System.currentTimeMillis();
        Object proceed = invocation.proceed();
        StringBuilder str = new StringBuilder(100);
        str.append(((MappedStatement) invocation.getArgs()[0]).getId());
        str.append(": ");
        str.append("cost time ");
        str.append(System.currentTimeMillis()-t1);
        str.append(" ms.");
        String sqlInfo = str.toString();
        logger.debug(sqlInfo);
        return proceed;
    }

    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    public void setProperties(Properties properties) {

    }
}
