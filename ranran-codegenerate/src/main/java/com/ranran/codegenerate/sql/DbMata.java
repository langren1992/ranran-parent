package com.ranran.codegenerate.sql;

import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * 抽象数据库操作
 *
 * @author 曾睿
 * @create 2017-11-23 17:53
 **/
public abstract class DbMata {

    protected static Properties prop;

    protected static Connection connection;

    protected NamePattern namePattern = new HumpNamePattern();

    static {
        prop = new Properties();
        InputStream in = null;
        try {
            in = MysqlDataMeta.class.getResourceAsStream("/codeGenerator/db.properties");
            prop.load(in);
            prop.setProperty("remarks", "true"); //设置可以获取remarks信息
            prop.setProperty("useInformationSchema", "true");//设置可以获取tables remarks信息
            connection = DriverManager.getConnection(prop.getProperty("url"), prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void closeResultSet(ResultSet resultSet){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void setNamePattern(NamePattern namePattern){
        this.namePattern = namePattern;
    }

    public DatabaseMetaData getDatabaseMetaData(){
        try {
            return connection.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract List<TableInfo> getTableInfo(String tableName)  throws SQLException;

    public static Properties getProp() {
        return prop;
    }
}
