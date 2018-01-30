package com.ranran;

import com.ranran.test.dao.TestTsRoleDao;
import com.ranran.test.service.TsRoleService;
import com.ranran.test.service.impl.TsRoleServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.ranran")
@MapperScan("com.ranran.test.dao")
public class App {
    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);

        /**
         * 客户端调用
         */
        //接口
//        TsRoleService tsRoleDao = (TsRoleService) Proxy.newProxyInstance(App.class.getClassLoader(), new Class[]{TsRoleService.class}, new InvocationHandler() {
//            //代理目标
//            TsRoleService target = new TsRoleServiceImpl();
//            //委托
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                System.out.println("实行前");
//                Object invoke = method.invoke(target, args);
//                System.out.println("执行后");
//                return invoke;
//            }
//        });
//        //代理对象
//        tsRoleDao.sayHi("sssssssssssssss");
    }
}
