package com.ranran.core.jdbc.mapper.base;

import com.ranran.core.jdbc.mapper.BaseDeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;

import java.util.List;

/**
 * Created by Administrator on 2016/11/5 0005.
 */
public interface DeleteMapper<T> {

    /**
     * 批量刪除通过 id集合
     * @params id集合
     *
     * */
    @InsertProvider(type = BaseDeleteProvider.class, method = "dynamicSQL")
    int deleteBatchByIds(List<T> t);

    /**
     * 批量刪除通过 对象集合
     * @params 对象集合
     *
     * */
    @Deprecated
    @InsertProvider(type = BaseDeleteProvider.class, method = "dynamicSQL")
    int deleteBatchByObjs(List<T> t);
}
