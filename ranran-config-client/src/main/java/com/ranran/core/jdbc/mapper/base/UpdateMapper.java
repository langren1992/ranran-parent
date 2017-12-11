package com.ranran.core.jdbc.mapper.base;

import com.ranran.core.jdbc.mapper.BaseUpdateProvider;
import org.apache.ibatis.annotations.InsertProvider;

import java.util.List;

/**
 * Created by Administrator on 2016/11/5 0005.
 */
public interface UpdateMapper<T> {

    @InsertProvider(type = BaseUpdateProvider.class, method = "dynamicSQL")
    int updateBatch(List<T> t);
}
