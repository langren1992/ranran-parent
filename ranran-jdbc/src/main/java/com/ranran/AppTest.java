package com.ranran;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.io.InputStream;

/**
 * Hello world!
 *
 */
public class AppTest {
    public static void main( String[] args ) {
        String resource = "Configuration.xml";
        InputStream inputStream;
        try {
            //MyBatis 包含一个名叫 Resources 的工具类，它包含一些实用方法，可使从 classpath 或其他位置加载资源文件
            inputStream = Resources.getResourceAsStream(resource);
            //SqlSessionFactoryBuild来创建一个SqlSessionFactory实例
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                    .build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("com.ranran.test.dao.TestTsRoleDao.insert", "110000");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
