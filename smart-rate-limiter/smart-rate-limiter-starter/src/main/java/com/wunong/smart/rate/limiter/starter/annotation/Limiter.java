package com.wunong.smart.rate.limiter.starter.annotation;

/**
 * 限流器
 *
 * @author created by zealot.zt
 */
public @interface Limiter {

    /**
     * 限流标识
     *
     * @return
     */
    String key() default "";

    /**
     * 限流提示信息
     *
     * @return
     */
    String tips() default "";

}
