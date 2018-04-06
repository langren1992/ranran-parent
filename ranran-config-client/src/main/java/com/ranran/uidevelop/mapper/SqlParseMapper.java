package com.ranran.uidevelop.mapper;

import com.ranran.core.jdbc.mapper.RanRanMapper;
import com.ranran.uidevelop.model.ColumnModel;
import com.ranran.uidevelop.model.SqlParseModel;
import com.ranran.uidevelop.model.TableModel;

import java.util.List;

public interface SqlParseMapper extends RanRanMapper<SqlParseModel> {

    List<TableModel> selectTables(TableModel tableModel);

    List<ColumnModel> selectTableColumns(ColumnModel columnModel);

}
