package com.wunong.smart.rate.limiter.core.support;

import com.wunong.smart.rate.limiter.core.api.KeyResolver;
import com.wunong.smart.rate.limiter.core.factory.KeyResolverFactory;
import com.wunong.smart.rate.limiter.core.factory.impl.DefaultKeyResolverFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author created by zealot.zt
 */
public class KeyResolverFactoryTest {

    private final KeyResolverFactory factory = new DefaultKeyResolverFactory();

    @Test
    public void testRegister() {
        String key = "testRegister";
        KeyResolver resolver = event -> key;
        Assert.assertNull(factory.get(resolver.getClass()));
        factory.register(resolver);
        Assert.assertNotNull(factory.get(resolver.getClass()));
    }

    @Test
    public void testRegisterNone() {
        String key = "testRegister";
        KeyResolver resolver = event -> key;
        Assert.assertNull(factory.get(KeyResolver.class));
        factory.register(resolver);
        Assert.assertNull(factory.get(KeyResolver.class));
    }

}
