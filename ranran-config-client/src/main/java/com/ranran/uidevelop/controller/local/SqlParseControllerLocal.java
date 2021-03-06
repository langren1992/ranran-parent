package com.ranran.uidevelop.controller.local;

import com.alibaba.fastjson.JSONObject;
import com.ranran.core.ErrorCode;
import com.ranran.core.ResponseResult;
import com.ranran.core.RestBaseController;
import com.ranran.uidevelop.controller.SqlParseController;
import com.ranran.uidevelop.entity.SqlSelectEntity;
import com.ranran.uidevelop.service.SqlParseService;
import com.ranran.uidevelop.vo.SqlParseVo;
import com.ranran.uidevelop.vo.TableColumnVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
@Component
public class SqlParseControllerLocal extends RestBaseController implements SqlParseController {

    @Autowired
    private SqlParseService sqlParseService;

    @Override
    public ResponseResult parseSql(HttpServletRequest request) {
        ResponseResult result = new ResponseResult();
        String reqData = this.wrapperJson(request, new ErrorCode(101,"/sql/parseSql.html"));
        SqlParseVo sqlParseVo = JSONObject.parseObject(reqData,SqlParseVo.class);
        SqlSelectEntity sqlSelectEntity = sqlParseService.parseSql(sqlParseVo);
        result.data = sqlSelectEntity;
        return result;
    }

    @Override
    public ResponseResult selectTables(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(101,"/sql/selectTables.html"));
        TableColumnVo sqlParseVo = JSONObject.parseObject(reqData,TableColumnVo.class);
        return sqlParseService.selectTables(sqlParseVo);
    }

    @Override
    public ResponseResult selectTableColumns(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(101,"/sql/selectTableColumns.html"));
        TableColumnVo sqlParseVo = JSONObject.parseObject(reqData,TableColumnVo.class);
        return sqlParseService.selectTableColumns(sqlParseVo);
    }
}
