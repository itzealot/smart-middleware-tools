package com.wunong.smart.rate.limiter.starter;

import com.wunong.smart.rate.limiter.core.factory.RateLimiterFactory;
import com.wunong.smart.rate.limiter.core.factory.impl.DefaultRateLimiterFactory;
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
    public RateLimiterFactory defaultRateLimiterFactory() {
        return new DefaultRateLimiterFactory();
    }

}
