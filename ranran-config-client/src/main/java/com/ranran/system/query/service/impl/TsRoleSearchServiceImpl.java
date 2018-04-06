package com.ranran.system.query.service.impl;


import com.ranran.system.query.service.base.TsRoleSearchServiceBase;
import com.ranran.system.query.vo.TsRoleSearchVo;
import com.ranran.system.mapper.TsRoleMapper;
import com.ranran.system.model.TsRole;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* Created by zengrui on 2017-12-03 22:00:49.
*/
@Service
public class TsRoleSearchServiceImpl extends TsRoleSearchServiceBase {

    @Autowired
    private TsRoleMapper tsRoleMapper;

    @Override
    public List queryByCondition(TsRoleSearchVo tsRoleSearchVo) {
        TsRole tsRole = new TsRole();
        BeanUtils.copyProperties(tsRoleSearchVo,tsRole);
        return tsRoleMapper.select(tsRole);
    }
}
