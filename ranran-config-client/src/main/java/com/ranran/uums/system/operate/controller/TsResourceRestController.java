package com.ranran.uums.system.operate.controller;

import com.ranran.core.ResponseResult;
import com.ranran.uums.system.model.TsResource;

import javax.servlet.http.HttpServletRequest;

public interface TsResourceRestController {

    public ResponseResult updateResData(HttpServletRequest request);
}
