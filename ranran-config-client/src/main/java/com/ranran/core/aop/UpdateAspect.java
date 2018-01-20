package com.ranran.core.aop;

import com.ranran.core.shiro.util.ShiroUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 监控Mapper下的方法的执行效率
 *
 * @author 曾睿
 * @create 2017-07-29 12:34
 **/
@Aspect
@Component
public class UpdateAspect {

    private static final Logger logger = LoggerFactory.getLogger(UpdateAspect.class);

    //修改人字段
    private static final String MODIFIER    = "modifier";

    //修改时间
    private static final String MODIFYTIME = "modifyTime";

    /**
     * 面向切面
     * @param joinPoint
     */
    @Before("execution(* com.ranran.uums.**.mapper.*Mapper.update*(..))")
    public void logServiceAccess(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        //登录人工号
        String userNo = (String)ShiroUtils.getSubject().getPrincipal();
        BeanWrapper beanWrapper;
        List list = new ArrayList();
        if(args[0] instanceof List){
            list = (List) args[0];
            for (Object object:list) {
                beanWrapper = new BeanWrapperImpl(object);
                if (beanWrapper.isWritableProperty(MODIFIER)) {
                    beanWrapper.setPropertyValue(MODIFIER, userNo);
                    beanWrapper.setPropertyValue(MODIFYTIME,new Timestamp(System.currentTimeMillis()));
                }
            }
        }else {
            beanWrapper = new BeanWrapperImpl(args);
            // 设置创建时间和修改时间
            if (beanWrapper.isWritableProperty(MODIFIER)) {
                beanWrapper.setPropertyValue(MODIFIER, userNo);
                beanWrapper.setPropertyValue(MODIFYTIME,new Timestamp(System.currentTimeMillis()));
            }
        }
    }
}
