package com.ranran.codegenerate.sql;


import com.ranran.codegenerate.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Mysql数据源
 *
 * @author 曾睿
 * @create 2017-11-23 17:01
 **/
public class MysqlDataMeta extends DbMata{

    @Override
    public List<TableInfo> getTableInfo(String tableNamePattern) throws SQLException {
        String schema = (String) super.getProp().get("schema");
        DatabaseMetaData databaseMetaData = super.connection.getMetaData();
        ResultSet tableRest = databaseMetaData.getTables(null, schema,tableNamePattern,new String[]{"TABLE", "VIEW"});
        NamePattern nameChanger = this.namePattern;
        List<TableInfo> tableInfoList = new ArrayList<TableInfo>();
        TableInfo tableInfo = null;
        while(tableRest.next()) {
            String tableName = tableRest.getString("TABLE_NAME");
            String className = nameChanger.getClassName(tableName);
            String remarks = tableRest.getString("REMARKS");
            tableInfo = new TableInfo(tableName,className,remarks);
            tableInfoList.add(tableInfo);
        }
        ColumnInfo columnInfo = null;
        List<String> stringList = null;
        List<ColumnInfo> idColumnInfo = null;
        List<ColumnInfo> columnInfos = null;
        List<String> ids = new ArrayList<String>();
        for (int i = 0,size = tableInfoList.size(); i < size; i++) {
            stringList = new LinkedList<String>();
            idColumnInfo = new LinkedList<ColumnInfo>();
            columnInfos = new LinkedList<ColumnInfo>();
            tableRest =  databaseMetaData.getPrimaryKeys(null, schema, tableInfoList.get(i).getName());
            while(tableRest.next()) {
                String colName = tableRest.getString("COLUMN_NAME");
                stringList.add(colName);
            }
            tableRest = databaseMetaData.getColumns(null, schema, tableInfoList.get(i).getName(), "%");
            while(tableRest.next()) {
                String columnName = tableRest.getString("COLUMN_NAME");
                if(stringList.contains(columnName)){
                    columnInfo = transToColumnInfo(nameChanger,tableRest);
                    columnInfo.setPrimaryKey(true);
                    ids.add(columnInfo.getPropertyName());
                    idColumnInfo.add(columnInfo);
                }else{
                    columnInfo = transToColumnInfo(nameChanger,tableRest);
                    columnInfo.setPrimaryKey(false);
                    columnInfos.add(columnInfo);
                }
            }
            tableInfoList.get(i).setIdColumns(idColumnInfo);
            tableInfoList.get(i).setColumns(columnInfos);
            tableInfoList.get(i).setIds(ids.toString().substring(1,ids.toString().length()-1));
        }
        this.closeResultSet(tableRest);
        return tableInfoList;
    }

    private static ColumnInfo transToColumnInfo(NamePattern nameChanger, ResultSet tableRest) throws SQLException {
        String columnName = tableRest.getString("COLUMN_NAME");
        String propertyName = nameChanger.getPropertyName(columnName);
        String columnType = tableRest.getString("TYPE_NAME");
        Integer dataType = Integer.valueOf(tableRest.getInt("DATA_TYPE"));
        Integer columnSize = Integer.valueOf(tableRest.getInt("COLUMN_SIZE"));
        Object decimalDigits = tableRest.getObject("DECIMAL_DIGITS");
        Integer digit = null;
        if(decimalDigits != null) {
            digit = Integer.valueOf(((Number)decimalDigits).intValue());
        }
        String propertyType = JavaType.getType(dataType,columnSize,columnSize);
        String remark = tableRest.getString("REMARKS");
        return  new ColumnInfo(columnName,remark,columnType,propertyName,propertyType,columnSize,digit);
    }

}