package com.ranran.uums.system.operate.controller;

import com.ranran.core.ResponseResult;
import com.ranran.uums.system.operate.vo.TsDistrictSelectVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 
 * @creator zengrui 2018-02-03 05:27
 */
public interface TsDistrictRestController {

    /**
     * 查询
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    ResponseResult selectTsDistrict(HttpServletRequest request);


    /**
     * 新增、启用、停用、删除（逻辑删除）
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    ResponseResult updateTsDistricts(HttpServletRequest request);

    /**
     * 删除（物理删除）
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    ResponseResult deleteTsDistricts(HttpServletRequest request);

    /**
     * 导入
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    ResponseResult importTsDistricts(HttpServletRequest request);

    /**
     * 导出
     * @param response 响应结果
     * @return ResponseResult 响应结果
     */
    void exportTsDistricts(TsDistrictSelectVo tsDistrictSelectVo, HttpServletResponse response);

    /**
     * 导出
     * @param response 响应结果
     * @return ResponseResult 响应结果
     */
    void downloadTsDistrict(HttpServletResponse response);
}
