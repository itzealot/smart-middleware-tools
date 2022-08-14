package com.wunong.smart.rate.limiter.core.factory.impl;

import com.wunong.smart.rate.limiter.core.api.IRateLimiter;
import com.wunong.smart.rate.limiter.core.config.LimiterData;
import com.wunong.smart.rate.limiter.core.enums.LimiterEnum;
import com.wunong.smart.rate.limiter.core.exception.DegradedException;
import com.wunong.smart.rate.limiter.core.exception.LimitedException;
import com.wunong.smart.rate.limiter.core.factory.RateLimiterFactory;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author created by zealot.zt
 */
public class DefaultRateLimiterFactory implements RateLimiterFactory {

    private final Map<String, IRateLimiter> map;

    public DefaultRateLimiterFactory() {
        this(32);
    }

    public DefaultRateLimiterFactory(int size) {
        this.map = new ConcurrentHashMap<>(size);
    }

    @Override
    public void tryLimit(String key) throws LimitedException {
        LimiterEnum limiterEnum = tryLimitKey(key);

        if (limiterEnum == LimiterEnum.DEGRADE) {
            afterDegradedCallback(key);
            throw new DegradedException();
        } else if (limiterEnum == LimiterEnum.LIMITED) {
            afterLimitedCallback(key);
            throw new LimitedException();
        }
    }

    @Override
    public void tryLimit(Method method) throws LimitedException {
        String key = getMethodKey(method);
        this.tryLimit(key);
    }

    @Override
    public void tryLimit(LimiterData data) throws LimitedException, DegradedException {
        String key = data.getKey();
        LimiterEnum limiterEnum = tryLimitKey(key);

        if (limiterEnum == LimiterEnum.DEGRADE) {
            afterDegradedCallback(key);
            throw new DegradedException(data.getTips());
        } else if (limiterEnum == LimiterEnum.LIMITED) {
            afterLimitedCallback(key);
            throw new LimitedException(data.getTips());
        }
    }

    /**
     * 限流之后的回调方法
     *
     * @param key
     */
    protected void afterLimitedCallback(String key) {
        // do nothing
    }

    /**
     * 降级之后的回调方法
     *
     * @param key
     */
    protected void afterDegradedCallback(String key) {
        // do nothing
    }

    /**
     * 根据方法获取key标识
     *
     * @param method
     * @return
     */
    protected String getMethodKey(Method method) {
        StringBuilder builder = new StringBuilder(128)
                .append(method.getDeclaringClass().getName())
                .append(".")
                .append(method.getName());

        Class<?>[] classes = method.getParameterTypes();

        builder.append("(");
        if (classes.length > 0) {
            for (Class<?> clazz : classes) {
                builder.append(clazz.getSimpleName()).append(",");
            }
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.append(")").toString();
    }

    /**
     * 尝试限流
     *
     * @param key
     * @return
     */
    protected final LimiterEnum tryLimitKey(String key) {
        return tryLimitKey(key, 1);
    }

    /**
     * 尝试限流key
     *
     * @param key     限流key
     * @param permits 许可
     * @return
     */
    protected LimiterEnum tryLimitKey(String key, int permits) {
        IRateLimiter rateLimiter = map.get(key);

        if (rateLimiter == null) {
            return LimiterEnum.PASS;
        }

        // 降级
        if (rateLimiter.isDegraded()) {
            return LimiterEnum.DEGRADE;
        }

        // 尝试获取许可，获取到则通过
        if (rateLimiter.tryAcquire(permits)) {
            return LimiterEnum.PASS;
        }

        return LimiterEnum.LIMITED;
    }

    @Override
    public void register(String key, IRateLimiter limiter) {
        Objects.requireNonNull(limiter, "限流控制器必填");
        Assert.hasText(key, "限流key必填");
        map.put(key, limiter);
    }

}
