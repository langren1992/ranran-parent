package com.ranran.sql.service;

import com.ranran.core.ResponseResult;
import com.ranran.sql.entity.SqlSelectEntity;
import com.ranran.sql.model.ColumnModel;
import com.ranran.sql.model.TableModel;
import com.ranran.sql.vo.SqlParseVo;
import com.ranran.sql.vo.TableColumnVo;

import java.util.List;

public interface SqlParseService {

    SqlSelectEntity parseSql(SqlParseVo sqlParseVo);

    ResponseResult selectTables(TableColumnVo tableColumnVo);

    ResponseResult selectTableColumns(TableColumnVo tableColumnVo);
}
