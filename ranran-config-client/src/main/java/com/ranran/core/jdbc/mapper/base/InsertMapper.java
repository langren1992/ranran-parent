package com.ranran.core.jdbc.mapper.base;

import com.ranran.core.jdbc.mapper.BaseInsertProvider;
import org.apache.ibatis.annotations.InsertProvider;

import java.util.List;

/**
 * Created by 曾睿 on 2016/11/1.
 */
public interface InsertMapper<T> {

    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    int insertBatch(List<T> t);

}