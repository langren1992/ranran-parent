package com.ranran.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 监控Mapper下的方法的执行效率
 *
 * @author 曾睿
 * @create 2017-07-29 12:34
 **/
@Aspect
@Component
public class MapperAspect {

    private static final Logger logger = LoggerFactory.getLogger(MapperAspect.class);

    @AfterReturning("execution(* com.ranran.uums.**.controller.*ControllerLocal.*(..))")
    public void logServiceAccess(JoinPoint joinPoint) {
        logger.info("Completed: " + joinPoint);
    }


    /**
     * 监控com.monkey.system..*Mapper包及其子包的所有public方法
     */
    @Pointcut("execution(* com.ranran.uums.**.controller.*Controller.*(..))")
    private void pointCutMethod() {
    }

    /**
     * 声明环绕通知
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.nanoTime();
        Object obj = pjp.proceed();
        long end = System.nanoTime();
        logger.info("调用Mapper方法：{}，参数：{}，耗时：{}毫秒", pjp.getSignature().toString(), Arrays.toString(pjp.getArgs()), (end - begin) / 1000000);
        return obj;
    }
}
