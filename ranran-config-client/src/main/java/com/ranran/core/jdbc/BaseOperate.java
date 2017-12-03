package com.ranran.core.jdbc;

import java.sql.SQLException;
import java.util.List;

public interface BaseOperate<T> {

    public List<T> select(T t) throws JdbcException, IllegalAccessException;

    public List<T> selectAll();

    public List<T> selectOne(Object key);

    public void insert(T t);

    public void insertBatch(List<T> ts);

    public void update(T t);

    public void updateBatch(T t,Object keys);

    public void delete(T t);

    public void deleteBatch(List<T> ts);
}
