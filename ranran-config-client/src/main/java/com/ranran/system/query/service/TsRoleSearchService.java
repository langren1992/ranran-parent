package com.ranran.system.query.service;

import com.ranran.system.query.vo.TsRoleSearchVo;

import java.util.List;

/**
* Created by zengrui on 2017-12-03 22:00:48.
*/
public interface TsRoleSearchService {


    List queryByCondition(TsRoleSearchVo tsRoleSearchVo);
}
