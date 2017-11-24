package com.ranran;

import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.gen.GenConfig;

/**
 * @author 曾睿
 * @create 2017-11-22 21:31
 **/
public class CodeGenerator {

    public static void main(String[] args) throws Exception {
        ConnectionSource source = ConnectionSourceHelper.getSimple("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/my-tms?useUnicode=true&characterEncoding=utf-8", "root", "123456");
        DBStyle mysql = new MySqlStyle();
        // sql语句放在classpagth的/sql 目录下
        SQLLoader loader = new ClasspathLoader("/sql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
        UnderlinedNameConversion nc = new  UnderlinedNameConversion();
        // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
        SQLManager sqlManager = new SQLManager(mysql,loader,source,nc,new Interceptor[]{new DebugInterceptor()});
        // 或者直接生成java文件
        GenConfig config = new GenConfig();
        config.preferBigDecimal(true);
        sqlManager.genPojoCode("Ts_role","com.test",config);
    }
}
