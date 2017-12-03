package com.ranran.jdbc.core;

import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JdbcOperator extends AbstractJdbcOperator implements BaseJdbcOperator {

    /**
     * 全表信息查询
     *
     * @param t
     * @return
     * @throws JdbcException
     * @serialData 2017-11-28
     */
    @Override
    public <T> List<T> selectAll(Class<T> t) throws JdbcException {
        //检查表是否存在
        if(!t.isAnnotationPresent(Table.class)){
            throw new JdbcException("This "+t.getName()+" has no table name.");
        }
        Table table = t.getAnnotation(Table.class);
        StringBuffer selectSql = new StringBuffer();
        //获取私有属性，组装select语句
        selectSql.append(SqlOperator.doWrappedSelectSql(t.getDeclaredFields()));
        //添加表名
        selectSql.append(table.name());
        return this.jdbcTemplate.query(selectSql.toString(),this.getBeanPropertyRowMapper(t));
    }

    /**
     * 根据查询条件查询信息
     *
     * @param t
     * @param conditions
     * @return
     * @throws JdbcException
     */
    @Override
    public <T> List<T> selectByCondition(Class<T> t, SelectCondition[] conditions) throws JdbcException {
        //检查表是否存在
        if(!t.isAnnotationPresent(Table.class)){
            throw new JdbcException("This "+t.getName()+" has no table name.");
        }
        Table table = t.getAnnotation(Table.class);
        StringBuffer selectSql = new StringBuffer();
        //获取私有属性，组装select语句
        selectSql.append(SqlOperator.doWrappedSelectSql(t.getDeclaredFields()));
        //添加表名
        selectSql.append(table.name());
        //添加where语句
        selectSql.append(SqlOperateType.WHERE.name+SqlOperator.doWrappedWhereSql(conditions));
        return jdbcTemplate.query(selectSql.toString(),this.getBeanPropertyRowMapper(t));
    }

    /**
     * 根据对象查询信息
     *
     * @param t
     * @param object
     * @return
     * @throws JdbcException
     */
    @Override
    public <T> T selectByObject(Class<T> t,Object object) throws JdbcException {
        //检查表是否存在
        if(!t.isAnnotationPresent(Table.class)){
            throw new JdbcException("This "+t.getName()+" has no table name.");
        }
        if (!t.getName().equals(object.getClass().getName())){
            throw new JdbcException("This "+t.getName()+" has not the same with "+object.getClass().getName()+".");
        }
        Table table = t.getAnnotation(Table.class);
        StringBuffer selectSql = new StringBuffer();
        //获取私有属性，组装select语句
        selectSql.append(SqlOperator.doWrappedSelectSql(t.getDeclaredFields()));
        //添加表名
        selectSql.append(table.name());
        List<SelectCondition> selectConditions = new ArrayList<SelectCondition>();
        return null;
    }

    /**
     * 根据主键查询信息，只适用于单主键查询
     *
     * @param t
     * @param key
     * @return
     * @throws JdbcException
     */
    @Override
    public <T> T selectById(Class<T> t, Object key) throws JdbcException {
        //检查表是否存在
        if(!t.isAnnotationPresent(Table.class)){
            throw new JdbcException("This "+t.getName()+" has no table name.");
        }
        Table table = t.getAnnotation(Table.class);
        StringBuffer selectSql = new StringBuffer();
        //获取私有属性，组装select语句
        selectSql.append(SqlOperator.doWrappedSelectSql(t.getDeclaredFields()));
        //添加表名
        selectSql.append(table.name());
        Field[] fields = t.getDeclaredFields();
        for (int i = 0,length = fields.length; i < length; i++) {
            if(fields[i].isAnnotationPresent(Id.class)){
                fields[i].setAccessible(true);
                Id id = fields[i].getAnnotation(Id.class);
                selectSql.append(SqlOperateType.WHERE.name+SqlOperator.doWrappedWhereSql(new SelectCondition[]{new SelectCondition(fields[i].getName(),key)}));
                return (T) jdbcTemplate.queryForObject(selectSql.toString(),this.getBeanPropertyRowMapper(t));
            }
        }
        throw new JdbcException("This "+t.getName()+" has no id name.");
    }

}
