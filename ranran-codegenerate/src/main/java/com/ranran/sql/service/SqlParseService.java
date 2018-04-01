package com.ranran.sql.service;

import com.ranran.sql.entity.SqlSelectEntity;

public interface SqlParseService {

    SqlSelectEntity parseSql(String sql);
}
