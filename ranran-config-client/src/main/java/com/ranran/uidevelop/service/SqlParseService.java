package com.ranran.uidevelop.service;

import com.ranran.core.ResponseResult;
import com.ranran.uidevelop.entity.SqlSelectEntity;
import com.ranran.uidevelop.vo.SqlParseVo;
import com.ranran.uidevelop.vo.TableColumnVo;

public interface SqlParseService {

    SqlSelectEntity parseSql(SqlParseVo sqlParseVo);

    ResponseResult selectTables(TableColumnVo tableColumnVo);

    ResponseResult selectTableColumns(TableColumnVo tableColumnVo);
}
