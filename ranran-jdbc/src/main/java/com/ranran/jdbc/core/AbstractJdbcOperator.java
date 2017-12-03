package com.ranran.jdbc.core;

import com.ranran.jdbc.AbstractModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractJdbcOperator<T>{

    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public BeanPropertyRowMapper<T> getBeanPropertyRowMapper(Class<T> t) {
        return BeanPropertyRowMapper.newInstance(t);
    }
}
