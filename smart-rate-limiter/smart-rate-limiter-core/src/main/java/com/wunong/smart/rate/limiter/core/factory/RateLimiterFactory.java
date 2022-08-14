package com.wunong.smart.rate.limiter.core.factory;

import com.wunong.smart.rate.limiter.core.api.IRateLimiter;
import com.wunong.smart.rate.limiter.core.config.LimiterData;
import com.wunong.smart.rate.limiter.core.exception.DegradedException;
import com.wunong.smart.rate.limiter.core.exception.LimitedException;

import java.lang.reflect.Method;

/**
 * 限流工厂
 *
 * @author created by zealot.zt
 */
public interface RateLimiterFactory {

    /**
     * 尝试限流指定key
     *
     * @param key 被限流的key
     * @return
     * @throws LimitedException 限流成功抛出异常
     */
    void tryLimit(String key) throws LimitedException, DegradedException;

    /**
     * 尝试限流指定方法
     *
     * @param method
     * @return
     * @throws LimitedException
     */
    void tryLimit(Method method) throws LimitedException, DegradedException;

    /**
     * 尝试限流方法
     *
     * @param data
     * @throws LimitedException
     * @throws DegradedException
     */
    void tryLimit(LimiterData data) throws LimitedException, DegradedException;

    /**
     * 根据key注册限制器
     *
     * @param key
     * @param limiter
     */
    void register(String key, IRateLimiter limiter);

}
