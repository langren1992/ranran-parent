package com.ranran.core.jdbc.mapper;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Set;


public class BaseUpdateProvider extends MapperTemplate {

    public BaseUpdateProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String updateBatch(MappedStatement ms){
        Class<?> entityClass = getEntityClass(ms);
        StringBuffer sql = new StringBuffer();
        sql.append(SqlHelper.updateTable(entityClass, tableName(entityClass)));
        sql.append("<trim prefix=\"set\" suffixOverrides=\",\">");
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        Set<EntityColumn> pkColumns = EntityHelper.getPKColumns(entityClass);
        boolean flag = true;
        for (EntityColumn column : columnList) {
            sql.append("<trim prefix=\""+column.getColumn()+" =case\" suffix=\"end,\">");
            sql.append("<foreach collection=\"list\" item=\"i\" index=\"index\">");
            sql.append("<if test=\"i."+column.getProperty()+"!=null\">");
            sql.append(" when ");
            for (EntityColumn pkColumn : pkColumns) {
                if(flag){
                    sql.append(" "+pkColumn.getColumn()+"=#{i."+pkColumn.getProperty()+"}");
                } else {
                    sql.append(" AND "+pkColumn.getColumn()+"=#{i."+pkColumn.getProperty()+"}");
                }
                flag = false;
            }
            sql.append(" then #{i."+column.getProperty()+"}");
            sql.append("</if>");
            sql.append("</foreach>");
            sql.append("</trim>");
            flag = true;
        }
        sql.append("</trim>");
        sql.append("<where>");
        sql.append("<foreach collection=\"list\" separator=\"or\" item=\"i\" index=\"index\" >");
        flag = true;
        for (EntityColumn pkColumn : pkColumns) {
            if(flag){
                sql.append(" ("+pkColumn.getColumn()+"=#{i."+pkColumn.getProperty()+"}");
            } else {
                sql.append(" AND "+pkColumn.getColumn()+"=#{i."+pkColumn.getProperty()+"}");
            }
            sql.append(")");
            flag = false;
        }
        sql.append("</foreach>");
        sql.append("</where>");
        return sql.toString();
    }

}
