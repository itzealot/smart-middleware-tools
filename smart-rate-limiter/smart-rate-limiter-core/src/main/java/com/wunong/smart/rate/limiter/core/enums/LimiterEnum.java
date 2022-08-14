package com.wunong.smart.rate.limiter.core.enums;

/**
 * 控制器枚举
 *
 * @author created by zealot.zt
 */
public enum LimiterEnum {

    /**
     * 被降级
     */
    DEGRADE,

    /**
     * 被限流
     */
    LIMITED,

    /**
     * 通过
     */
    PASS,
    ;

}
