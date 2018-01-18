package com.ranran.core.aop;

import com.ranran.core.SnowflakeIdWorker;
import com.ranran.core.shiro.util.ShiroUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Id;
import java.lang.reflect.Field;
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
public class InertAspect {

    private static final Logger logger = LoggerFactory.getLogger(InertAspect.class);

    //创建者字段
    private static final String CREATOR     = "creator";

    //创建时间
    private static final String CREATETIME = "createTime";

    //修改人字段
    private static final String MODIFIER    = "modifier";

    //修改时间
    private static final String MODIFYTIME = "modifyTime";

    //id生成组件
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Before("execution(* com.ranran.uums.**.mapper.*Mapper.insert*(..))")
    public void logServiceAccess(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        //登录人工号
        String userNo = (String)ShiroUtils.getSubject().getPrincipal();
        //主键ID名称
        String idName = "";
        List<String> idList = new ArrayList<String>();
        BeanWrapper beanWrapper;
        List list = new ArrayList();
        if(args[0] instanceof List){
            list = (List) args[0];
            Object o = list.get(0);
            for (int i = 0,length= o.getClass().getDeclaredFields().length; i < length; i++) {
                Field field = o.getClass().getDeclaredFields()[i];
                if (field.isAnnotationPresent(Id.class)){
                    idList.add(field.getName());
                }
            }
            for (Object object:list) {
                beanWrapper = new BeanWrapperImpl(object);
                //对象ID主键赋值
                for (int i = 0,size = idList.size(); i < size; i++) {
                    beanWrapper.setPropertyValue(idList.get(i), snowflakeIdWorker.nextId());
                }
                // 设置创建时间和修改时间
                if (beanWrapper.isWritableProperty(CREATOR)) {
                    beanWrapper.setPropertyValue(CREATOR, userNo);
                    beanWrapper.setPropertyValue(CREATETIME,new Date());
                }
                if (beanWrapper.isWritableProperty(MODIFIER)) {
                    beanWrapper.setPropertyValue(MODIFIER, userNo);
                    beanWrapper.setPropertyValue(MODIFYTIME,new Date());
                }
            }
        }else {
            beanWrapper = new BeanWrapperImpl(args);
            // 设置创建时间和修改时间
            if (beanWrapper.isWritableProperty(CREATOR)) {
                beanWrapper.setPropertyValue(CREATOR, userNo);
                beanWrapper.setPropertyValue(CREATETIME,new Date());
            }
            if (beanWrapper.isWritableProperty(MODIFIER)) {
                beanWrapper.setPropertyValue(MODIFIER, userNo);
                beanWrapper.setPropertyValue(MODIFYTIME,new Date());
            }
        }
    }
}
