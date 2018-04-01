package com.ranran.sql.mapper;

import com.ranran.core.jdbc.mapper.RanRanMapper;
import com.ranran.sql.model.ColumnModel;
import com.ranran.sql.model.SqlParseModel;
import com.ranran.sql.model.TableModel;

import java.util.List;

public interface SqlParseMapper extends RanRanMapper<SqlParseModel> {

    List<TableModel> selectTables(TableModel tableModel);

    List<ColumnModel> selectTableColumns(ColumnModel columnModel);

}
