package com.ranran.core.jdbc;

import com.ranran.core.AbstractModel;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractJdbc<T extends AbstractModel>{

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<AbstractModel> select(AbstractModel o) throws JdbcException, IllegalAccessException {
        if(!"".equals(o.getTableName())&&null == o.getTableName()){
            throw new JdbcException("This "+o.getClass().getName()+" has no table name.");
        }
        return null;
    }

    public List selectAll() {
        return null;
    }

    public List selectOne(Object key) {
        return null;
    }

    public void insert(Object o) {

    }

    public void insertBatch(List list) {

    }

    public void update(Object o) {

    }

    public void updateBatch(Object o, Object keys) {

    }

    public void delete(Object o) {

    }

    public void deleteBatch(List list) {

    }
}
