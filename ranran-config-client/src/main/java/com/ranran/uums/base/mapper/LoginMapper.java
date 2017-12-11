package com.ranran.uums.base.mapper;

import com.ranran.core.BaseModel;
import com.ranran.core.jdbc.mapper.RanRanMapper;
import com.ranran.uums.system.model.TsResource;

import java.util.List;

public interface LoginMapper extends RanRanMapper<TsResource> {

    List<TsResource> selectUserForResource(String userNo);
}
