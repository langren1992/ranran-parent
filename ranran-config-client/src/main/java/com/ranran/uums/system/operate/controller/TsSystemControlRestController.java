package com.ranran.uums.system.operate.controller;

import com.ranran.core.ResponseResult;

import javax.servlet.http.HttpServletRequest;

/*
 * 系统控制参数
 * @creator zengrui 2018-01-30 10:13
 */
public interface TsSystemControlRestController {

    /**
     * 系统控制参数简称
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    public ResponseResult selectTsSystemControl(HttpServletRequest request);


    /**
     * 新增、启用、停用、删除（逻辑删除）系统控制参数
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    public ResponseResult updateTsSystemControls(HttpServletRequest request);


}
