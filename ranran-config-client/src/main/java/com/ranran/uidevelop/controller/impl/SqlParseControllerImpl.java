package com.ranran.uidevelop.controller.impl;

import com.ranran.core.ResponseResult;
import com.ranran.uidevelop.controller.SqlParseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController(value = "SqlParseController")
@RequestMapping("/sql")
public class SqlParseControllerImpl implements SqlParseController {

    @Autowired
    private SqlParseController selectTsSystemControl;

    /**
     * 查询系统控制参数信息
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    @PostMapping("/parseSql.html")
    public ResponseResult parseSql(HttpServletRequest request) {
        return selectTsSystemControl.parseSql(request);
    }

    @Override
    @PostMapping("/selectTables.html")
    public ResponseResult selectTables(HttpServletRequest request) {
        return selectTsSystemControl.selectTables(request);
    }

    @Override
    @PostMapping("/selectTableColumns.html")
    public ResponseResult selectTableColumns(HttpServletRequest request) {
        return selectTsSystemControl.selectTableColumns(request);
    }

}
