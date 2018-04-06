package com.ranran.uidevelop.service;

import com.ranran.uidevelop.entity.SqlSelectEntity;

public interface SqlParseService {

    SqlSelectEntity parseSql(String sql);
}
