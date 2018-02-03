package com.ranran.uums.system.operate.controller;

import com.ranran.core.ResponseResult;
import com.ranran.uums.system.operate.vo.TsSystemControlSelectVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
    ResponseResult selectTsSystemControl(HttpServletRequest request);


    /**
     * 新增、启用、停用、删除（逻辑删除）系统控制参数
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    ResponseResult updateTsSystemControls(HttpServletRequest request);

    /**
     * 删除（物理删除）系统控制参数
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    ResponseResult deleteTsSystemControls(HttpServletRequest request);

    /**
     * 导入系统控制参数
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    ResponseResult importTsSystemControls(HttpServletRequest request);

    /**
     * 导出系统控制参数
     * @param response 响应结果
     * @return ResponseResult 响应结果
     */
    void exportTsSystemControls(TsSystemControlSelectVo tsSystemControlSelectVo, HttpServletResponse response);

    /**
     * 导出系统控制参数
     * @param response 响应结果
     * @return ResponseResult 响应结果
     */
    void downloadTsSystemControl(HttpServletResponse response);



}
