package com.wunong.smart.rate.limiter.starter;

import com.wunong.smart.rate.limiter.core.api.MethodKeyProvider;
import com.wunong.smart.rate.limiter.core.factory.KeyResolverFactory;
import com.wunong.smart.rate.limiter.core.factory.RateLimiterFactory;
import com.wunong.smart.rate.limiter.core.factory.impl.DefaultKeyResolverFactory;
import com.wunong.smart.rate.limiter.core.factory.impl.DefaultRateLimiterFactory;
import com.wunong.smart.rate.limiter.core.support.DefaultMethodKeyProvider;
import com.wunong.smart.rate.limiter.starter.handler.InvokeEventHandler;
import com.wunong.smart.rate.limiter.starter.handler.impl.DefaultInvokeEventHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author created by zealot.zt
 */
@Configuration
public class RateLimiterRegisterConfiguration {

    /**
     * 扫码的包路径
     */
    public static final String PACKAGE = "com.wunong.smart.rate.limiter";

    @Bean
    @ConditionalOnMissingBean(RateLimiterFactory.class)
    public RateLimiterFactory defaultRateLimiterFactory(MethodKeyProvider methodKeyProvider) {
        DefaultRateLimiterFactory rateLimiterFactory = new DefaultRateLimiterFactory();
        rateLimiterFactory.setMethodKeyProvider(methodKeyProvider);
        return rateLimiterFactory;
    }

    @Bean
    @ConditionalOnMissingBean(MethodKeyProvider.class)
    public MethodKeyProvider defaultMethodKeyProvider() {
        return new DefaultMethodKeyProvider();
    }

    @Bean
    @ConditionalOnMissingBean(KeyResolverFactory.class)
    public KeyResolverFactory defaultKeyResolverFactory() {
        return new DefaultKeyResolverFactory();
    }

    @Bean
    @ConditionalOnMissingBean(InvokeEventHandler.class)
    public InvokeEventHandler defaultInvokeEventHandler() {
        return new DefaultInvokeEventHandler();
    }

}
