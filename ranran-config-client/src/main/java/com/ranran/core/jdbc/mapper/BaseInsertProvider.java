package com.ranran.core.jdbc.mapper;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Set;

/**
 * 曾睿
 */
public class BaseInsertProvider extends MapperTemplate {


    public BaseInsertProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 批量插入方法
     * */
    public String insertBatch(MappedStatement ms){
        Class<?> entityClass = getEntityClass(ms);
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        //开始拼sql
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.insertIntoTable(entityClass, tableName(entityClass)));
        sql.append(SqlHelper.insertColumns(entityClass, false, false, false));
        sql.append("VALUES");
        sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" >");
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        for (EntityColumn column : columnList) {
            sql.append("#{record.").append(column.getProperty()).append("},");
        }
        sql.append("</trim>");
        sql.append("</foreach>");
        return sql.toString();
    }

}
