package com.ranran.core.redis.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface RedisKey {

    /**
     * 键
     * @return
     */
    String key() default "";

    /**
     * 键的前面部分
     * @return
     */
    String prefix() default "";

    /**
     * 键的后面部分
     * @return
     */
    String suffix() default "";
}
