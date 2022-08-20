package com.wunong.smart.rate.limiter.starter.handler.impl;

import com.wunong.smart.rate.limiter.core.api.KeyProvider;
import com.wunong.smart.rate.limiter.core.api.KeyResolver;
import com.wunong.smart.rate.limiter.core.config.InvokeEvent;
import com.wunong.smart.rate.limiter.core.config.LimiterData;
import com.wunong.smart.rate.limiter.core.enums.LimitTypeEnum;
import com.wunong.smart.rate.limiter.core.factory.KeyResolverFactory;
import com.wunong.smart.rate.limiter.core.factory.RateLimiterFactory;
import com.wunong.smart.rate.limiter.starter.annotation.Limiter;
import com.wunong.smart.rate.limiter.starter.handler.InvokeEventHandler;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author created by zealot.zt
 */
public class DefaultInvokeEventHandler implements InvokeEventHandler {

    @Resource
    protected RateLimiterFactory rateLimiterFactory;

    @Resource
    protected KeyResolverFactory keyResolverFactory;

    @Override
    public void tryLimit(InvokeEvent event, Limiter limiter) {
        // 指定了解析器
        Class<? extends KeyResolver> clazz = limiter.resolver();
        if (Objects.nonNull(clazz) && !KeyResolver.class.equals(clazz)) {
            KeyResolver keyResolver = keyResolverFactory.get(clazz);
            Objects.requireNonNull(keyResolver, "can't find KeyResolver instance on " + clazz);
            rateLimiterFactory.tryLimit(LimiterData.create(keyResolver.resolve(event), limiter.tips(), LimitTypeEnum.KEY_RESOLVER));
            return;
        }

        // 限流参数中获取
        KeyProvider keyProvider = event.findKeyProvider();
        if (Objects.isNull(keyProvider)) {
            rateLimiterFactory.tryLimit(LimiterData.create(keyProvider.getKey(), limiter.tips(), LimitTypeEnum.KEY_PROVIDER));
            return;
        }

        // 指定了key
        if (StringUtils.isNotBlank(limiter.key())) {
            rateLimiterFactory.tryLimit(LimiterData.create(limiter.key(), limiter.tips(), LimitTypeEnum.LIMITER_KEY));
            return;
        }

        // 按照方法限流
        rateLimiterFactory.tryLimit(event.getMethod());
    }

}
