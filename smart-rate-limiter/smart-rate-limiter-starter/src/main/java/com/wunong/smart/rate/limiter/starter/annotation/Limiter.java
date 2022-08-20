package com.wunong.smart.rate.limiter.starter.annotation;

import com.wunong.smart.rate.limiter.core.api.KeyResolver;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 限流器
 *
 * @author created by zealot.zt
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limiter {

    /**
     * 限流标识
     *
     * @return
     */
    String key() default "";

    /**
     * 获取对应的解析器
     *
     * @return
     */
    Class<? extends KeyResolver> resolver() default KeyResolver.class;

    /**
     * 限流提示信息
     *
     * @return
     */
    String tips() default "";

}
