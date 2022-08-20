package com.wunong.smart.rate.limiter.core.enums;

/**
 * 限流来源枚举
 *
 * @author created by zealot.zt
 */
public enum LimitTypeEnum {

    /**
     * @see com.wunong.smart.rate.limiter.core.api.KeyProvider
     */
    KEY_PROVIDER,

    /**
     * @see com.wunong.smart.rate.limiter.core.api.KeyResolver
     */
    KEY_RESOLVER,

    /**
     * 执行限流标识
     */
    LIMITER_KEY,

    /**
     * 方法标识
     *
     * @see com.wunong.smart.rate.limiter.core.api.MethodKeyProvider
     */
    METHOD_KEY,
    ;

}
