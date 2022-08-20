package com.wunong.smart.rate.limiter.core.factory;

import com.wunong.smart.rate.limiter.core.api.KeyResolver;

/**
 * key解析器工厂
 *
 * @author created by zealot.zt
 */
public interface KeyResolverFactory {

    /**
     * 获取限流解析器
     *
     * @param clazz
     * @return
     */
    KeyResolver get(Class<? extends KeyResolver> clazz);

    /**
     * 注册限流解析器
     *
     * @param resolver
     */
    void register(KeyResolver resolver);

}
