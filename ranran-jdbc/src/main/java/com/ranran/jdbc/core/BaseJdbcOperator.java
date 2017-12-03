package com.ranran.jdbc.core;

import java.util.List;

public interface BaseJdbcOperator {

    /**
     * 全表信息查询
     * @param t
     * @param <T>
     * @return
     * @throws JdbcException
     * @serialData 2017-11-28
     */
    <T> List<T> selectAll(Class<T> t) throws JdbcException;

    /**
     * 根据查询条件查询信息
     * @param t
     * @param conditions
     * @param <T>
     * @return
     * @throws JdbcException
     */
    <T> List<T> selectByCondition(Class<T> t,SelectCondition[] conditions) throws JdbcException;

    /**
     * 根据对象查询信息
     * @param t
     * @param object
     * @param <T>
     * @return
     * @throws JdbcException
     */
    <T> T selectByObject(Class<T> t,Object object) throws JdbcException;

    /**
     * 根据主键查询信息
     * @param t
     * @param key
     * @param <T>
     * @return
     * @throws JdbcException
     */
    <T> T selectById(Class<T> t,Object key) throws JdbcException;

}

