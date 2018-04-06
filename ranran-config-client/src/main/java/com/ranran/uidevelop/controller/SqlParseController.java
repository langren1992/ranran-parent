package com.ranran.uidevelop.controller;

import com.ranran.core.ResponseResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title SqlParseController
 * @description SQL解析控制器
 * @author zengrui
 * @createTime 2018/4/6 11:51
 * @modifyTime 2018/4/6 11:51
 **/
public interface SqlParseController {

    ResponseResult parseSql(HttpServletRequest request);

    ResponseResult selectTables(HttpServletRequest request);

    ResponseResult selectTableColumns(HttpServletRequest request);
}
