package com.ranran.core.jdbc.mapper;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Set;

public class BaseDeleteProvider extends MapperTemplate {

    public BaseDeleteProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 批量刪除通过 id集合
     * @params id集合
     *
     * */
    public String deleteBatchByIds(MappedStatement ms){
        StringBuilder sql = new StringBuilder();
        Class<?> entityClass = getEntityClass(ms);
        //获取id
        String id = null;
        sql.append(SqlHelper.deleteFromTable(entityClass, tableName(entityClass)));
        sql.append("<where>");
        //获取全部列
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        //当某个列有主键策略时，不需要考虑他的属性是否为空，因为如果为空，一定会根据主键策略给他生成一个值
        for (EntityColumn column : columnList) {
            if(column.isId()){
                id = column.getProperty();
                sql.append(column.getColumn()+" in");
            }
        }
        sql.append(" (<foreach collection=\"list\" item=\"record\" separator=\",\" >");
        sql.append("#{record.").append(id).append("}");
        sql.append("</foreach> )");
        sql.append("</where>");
        return sql.toString();
    }

//    /**
//     * 批量刪除通过 对象集合
//     * @params 对象集合 删除该方法
//     *
//     * */
//    @Deprecated
//    public String deleteBatchByObjs(MappedStatement ms){
//        StringBuilder sql = new StringBuilder();
//        Class<?> entityClass = getEntityClass(ms);
//        sql.append(SqlHelper.deleteFromTable(entityClass, tableName(entityClass)));
//        sql.append("<where>");
//        sql.append(" <foreach collection=\"list\" item=\"record\" open=\"(\" separator=\" OR \" close=\")\"> (");
//        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
//        boolean first = true;
//        //当某个列有主键策略时，不需要考虑他的属性是否为空，因为如果为空，一定会根据主键策略给他生成一个值
//        for (EntityColumn column : columnList) {
//            if(first) {
//                sql.append("<if test=\"record."+column.getProperty()+"!= null and record." + column.getProperty() + "!='' \">");
//                sql.append(column.getColumn()+"=#{record."+column.getProperty()+"}");
//                sql.append("</if>");
//            }
//            sql.append("<if test=\"record."+column.getProperty()+"!= null and record." + column.getProperty() + "!='' \">");
//            sql.append(" AND "+column.getColumn()+"=#{record."+column.getProperty()+"}");
//            sql.append("</if>");
//            first = false;
//        }
//        sql.append(" )</foreach> ");
//        sql.append("</where>");
//        return sql.toString();
//    }

}
