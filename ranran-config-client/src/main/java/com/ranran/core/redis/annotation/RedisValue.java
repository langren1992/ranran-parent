package com.ranran.core.redis.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface RedisValue {
    /**
     * 值的前面部分
     * @return
     */
    String prefix() default "";

    /**
     * 值的后面部分
     * @return
     */
    String suffix() default "";
}
