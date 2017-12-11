package com.ranran.core.shiro.bind.annotation;

import com.ranran.core.shiro.util.Constants;

import java.lang.annotation.*;

/**
 * @ClassName: CurrentUser
 * @Description: 绑定当前登录的用户，不同于@ModelAttribute(这里用一句话描述这个类的作用)
 * @author Administratortom
 * @date 2016年6月11日 下午4:42:58
 *
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

    /**
     * @Title: value
     * @Description: 当前用户在request中的名字(这里用一句话描述这个方法的作用)
     * @author Administrator
     * @return String 返回类型
     * @throws
     */
    String value() default Constants.CURRENT_USER;
}

