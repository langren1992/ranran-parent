package com.ranran.core.jdbc.mapper;


import tk.mybatis.mapper.common.Mapper;

/**
 * Created by zengrui on 2017/7/18.
 */
public interface RanRanMapper<T> extends Mapper<T>,BaseBatchMapper<T> {

    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
