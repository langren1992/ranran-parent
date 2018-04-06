package com.ranran.base.mapper;

import com.ranran.core.jdbc.mapper.RanRanMapper;
import com.ranran.system.model.TsResource;

import java.util.List;

public interface LoginResMapper extends RanRanMapper<TsResource> {

    List<TsResource> selectUserForResource(String userNo);
}
