package com.ranran.core.jdbc.mapper;


import com.ranran.core.jdbc.mapper.base.DeleteMapper;
import com.ranran.core.jdbc.mapper.base.InsertMapper;
import com.ranran.core.jdbc.mapper.base.UpdateMapper;

public interface BaseBatchMapper<T> extends DeleteMapper<T>,InsertMapper<T>,UpdateMapper<T> {
}
