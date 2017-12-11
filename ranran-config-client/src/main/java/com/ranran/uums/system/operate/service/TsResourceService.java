package com.ranran.uums.system.operate.service;

import com.ranran.uums.system.model.TsResource;
import com.ranran.uums.system.operate.vo.TsResourceVo;

import java.util.List;
import java.util.Set;

/**
 * Created by zengrui on 2017/7/17.
 */
public interface TsResourceService {

    public List select(TsResource tsResource);

    public int updateResData(TsResourceVo tsResourceVo);
}

