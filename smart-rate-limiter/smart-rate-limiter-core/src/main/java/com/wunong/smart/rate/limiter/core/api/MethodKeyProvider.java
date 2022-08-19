package com.wunong.smart.rate.limiter.core.api;

import com.wunong.smart.rate.limiter.core.support.DefaultMethodKeyProvider;

import java.lang.reflect.Method;

/**
 * 方法key限流生成器
 *
 * @author created by zealot.zt
 */
public interface MethodKeyProvider {

    /**
     * 方法限流key生成器
     */
    MethodKeyProvider DEFAULT_PROVIDER = new DefaultMethodKeyProvider();

    /**
     * 根据方法限流key标识
     *
     * @param method
     * @return
     */
    String getKey(Method method);

}
