package com.wunong.smart.rate.limiter.core.factory.impl;

import com.wunong.smart.rate.limiter.core.api.KeyResolver;
import com.wunong.smart.rate.limiter.core.factory.KeyResolverFactory;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author created by zealot.zt
 */
public class DefaultKeyResolverFactory implements KeyResolverFactory {

    /**
     * 限流key解析器
     */
    private final Map<Class<? extends KeyResolver>, KeyResolver> map;

    public DefaultKeyResolverFactory() {
        this(32);
    }

    public DefaultKeyResolverFactory(int size) {
        this.map = new ConcurrentHashMap<>(size);
    }

    @Override
    public KeyResolver get(Class<? extends KeyResolver> clazz) {
        return map.get(clazz);
    }

    @Override
    public void register(KeyResolver resolver) {
        Objects.requireNonNull(resolver, "resolver can't be null");
        map.put(resolver.getClass(), resolver);
    }

}
