package com.wunong.smart.rate.limiter.core.factory.impl;

import com.wunong.smart.rate.limiter.core.api.IRateLimiter;
import com.wunong.smart.rate.limiter.core.api.MethodKeyProvider;
import com.wunong.smart.rate.limiter.core.callback.DegradedCallback;
import com.wunong.smart.rate.limiter.core.callback.LimitedCallback;
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

    /**
     * 限流配置
     */
    private final Map<String, IRateLimiter> map;

    /**
     * 方法限流标识
     */
    private MethodKeyProvider methodKeyProvider = MethodKeyProvider.DEFAULT_PROVIDER;

    /**
     * 降级回调
     */
    private DegradedCallback degradedCallback = DegradedCallback.DEFAULT_CALLBACK;

    /**
     * 限流回调
     */
    private LimitedCallback limitedCallback = LimitedCallback.DEFAULT_CALLBACK;

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
            degradedCallback.callback(key);
        } else if (limiterEnum == LimiterEnum.LIMITED) {
            limitedCallback.callback(key);
        }
    }

    @Override
    public void tryLimit(Method method) throws LimitedException {
        String key = methodKeyProvider.getKey(method);
        this.tryLimit(key);
    }

    @Override
    public void tryLimit(LimiterData data) throws LimitedException, DegradedException {
        String key = data.getKey();
        LimiterEnum limiterEnum = tryLimitKey(key);

        if (limiterEnum == LimiterEnum.DEGRADE) {
            degradedCallback.callback(data);
        } else if (limiterEnum == LimiterEnum.LIMITED) {
            limitedCallback.callback(data);
        }
    }

    /**
     * 设置限流回调方法
     *
     * @param limitedCallback
     */
    public void setLimitedCallback(LimitedCallback limitedCallback) {
        Objects.requireNonNull(limitedCallback, "limited callback can't be null");
        this.limitedCallback = limitedCallback;
    }

    /**
     * 设置降级回调方法
     *
     * @param degradedCallback
     */
    public void setDegradedCallback(DegradedCallback degradedCallback) {
        Objects.requireNonNull(degradedCallback, "degraded callback can't be null");
        this.degradedCallback = degradedCallback;
    }

    /**
     * 设置方法标识提供者
     *
     * @param methodKeyProvider
     */
    public void setMethodKeyProvider(MethodKeyProvider methodKeyProvider) {
        Objects.requireNonNull(methodKeyProvider, "method key provider can't be null");
        this.methodKeyProvider = methodKeyProvider;
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
