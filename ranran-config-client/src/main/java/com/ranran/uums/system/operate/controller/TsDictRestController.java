package com.ranran.uums.system.operate.controller;

import com.ranran.core.ResponseResult;

import javax.servlet.http.HttpServletRequest;

/*
 * 数据字典
 * @creator zengrui 2018-01-21 11:14
 */
public interface TsDictRestController {

    /**
     * 数据字典简称
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    public ResponseResult selectTsDict(HttpServletRequest request);

    /**
     * 数据字典资源信息
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    public ResponseResult selectTsDictTsResource(HttpServletRequest request);


    /**
     * 新增、启用、停用、删除（逻辑删除）数据字典
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    public ResponseResult updateTsDicts(HttpServletRequest request);


}
