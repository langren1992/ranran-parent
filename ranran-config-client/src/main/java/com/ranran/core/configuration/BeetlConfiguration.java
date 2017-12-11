package com.ranran.core.configuration;

import com.ranran.core.shiro.util.ShiroExt;
import org.beetl.core.resource.WebAppResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.JPA2NameConversion;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 曾睿
 * @create 2017-11-21 16:07
 **/
@Configuration
public class BeetlConfiguration {

    @Bean(initMethod = "init", name = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {

        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
        try {
            String root =  patternResolver.getResource("classpath:templates").getFile().toString();
            WebAppResourceLoader webAppResourceLoader = new WebAppResourceLoader(root);
            beetlGroupUtilConfiguration.setResourceLoader(webAppResourceLoader);
            //手动注入自定义方法
            Map<String, Object> functionPackages = new HashMap<String, Object>();
            functionPackages.put("so", new ShiroExt());
            beetlGroupUtilConfiguration.setFunctionPackages(functionPackages);
            return beetlGroupUtilConfiguration;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        return beetlSpringViewResolver;
    }

//    @Bean(name = "beetlSqlScannerConfigurer")
//    public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer() {
//        BeetlSqlScannerConfigurer conf = new BeetlSqlScannerConfigurer();
//        conf.setBasePackage("com.ranran");
//        conf.setDaoSuffix("Mapper");
//        conf.setSqlManagerFactoryBeanName("sqlManagerFactoryBean");
//        return conf;
//    }

//    @Bean(name = "sqlManagerFactoryBean")
//    @Primary
//    public SqlManagerFactoryBean getSqlManagerFactoryBean(@Qualifier("dataSource") DataSource datasource) {
//        SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
//        BeetlSqlDataSource  source = new BeetlSqlDataSource();
//        source.setMasterSource(datasource);
//        factory.setCs(source);
//        factory.setDbStyle(new MySqlStyle());
//        factory.setInterceptors(new Interceptor[]{new DebugInterceptor()});
//        factory.setNc(new JPA2NameConversion());
//        factory.setSqlLoader(new ClasspathLoader("/sql"));
//        return factory;
//    }

//    @Bean(name="txManager")
//    public DataSourceTransactionManager getDataSourceTransactionManager(@Qualifier("dataSource") DataSource datasource) {
//        DataSourceTransactionManager dsm = new DataSourceTransactionManager();
//        dsm.setDataSource(datasource);
//        return dsm;
//    }

}
