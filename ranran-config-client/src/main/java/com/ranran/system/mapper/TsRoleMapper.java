package com.ranran.system.mapper;
import com.ranran.system.model.TsRole;
import com.ranran.core.jdbc.mapper.RanRanMapper;

import java.util.List;

/*
* 角色表
* gen mapper 2017-12-10 14:43:09
*/
public interface TsRoleMapper extends RanRanMapper<TsRole> {
    List selectRoleUser(TsRole tsRole);
}