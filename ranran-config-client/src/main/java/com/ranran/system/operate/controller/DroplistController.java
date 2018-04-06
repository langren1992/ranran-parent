package com.ranran.system.operate.controller;

import com.ranran.core.ResponseResult;

import javax.servlet.http.HttpServletRequest;
/**
 * @Title DroplistController
 * @description 下拉列表查询控制列表
 * @author zengrui
 * @createTime 2018/4/6 21:36
 * @modifyTime 2018/4/6 21:36
 **/
public interface DroplistController {

    /**
     * @Title queryDropListData
     * @description 查询下拉列表数据
     * @param request HttpServletRequest
     * @author zengrui
     * @createTime 2018/4/6 21:36
     * @modifyTime 2018/4/6 21:36
     * @return ResponseResult
     **/
    public ResponseResult queryDropListData(HttpServletRequest request);
}
