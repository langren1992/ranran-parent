package com.ranran.sql.controller.impl;

import com.ranran.sql.controller.SqlParseController;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class SqlParseControllerImpl implements SqlParseController {

    @Autowired
    private SqlParseController selectTsSystemControl;

    /**
     * 查询系统控制参数信息
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
//    @Override
//    @PostMapping("/selectTsSystemControl.html")
//    public ResponseResult selectTsSystemControl(HttpServletRequest request) {
//        return selectTsSystemControl.selectTsSystemControl(request);
//    }
}
