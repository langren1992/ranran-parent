package com.ranran.system.operate.service;

import com.ranran.system.model.TsResource;
import com.ranran.system.operate.vo.TsResourceVo;

import java.util.List;

/**
 * Created by zengrui on 2017/7/17.
 */
public interface TsResourceService {

    public List select(TsResource tsResource);

    public int updateResData(TsResourceVo tsResourceVo);
}

