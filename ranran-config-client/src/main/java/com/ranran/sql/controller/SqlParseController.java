package com.ranran.sql.controller;

import com.ranran.core.ResponseResult;
import com.ranran.sql.vo.TableColumnVo;

import javax.servlet.http.HttpServletRequest;

public interface SqlParseController {
    ResponseResult parseSql(HttpServletRequest request);

    ResponseResult selectTables(HttpServletRequest request);

    ResponseResult selectTableColumns(HttpServletRequest request);
}
