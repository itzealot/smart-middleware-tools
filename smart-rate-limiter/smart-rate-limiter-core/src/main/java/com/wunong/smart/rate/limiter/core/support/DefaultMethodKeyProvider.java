package com.wunong.smart.rate.limiter.core.support;

import com.wunong.smart.rate.limiter.core.api.MethodKeyProvider;

import java.lang.reflect.Method;

/**
 * @author created by zealot.zt
 */
public class DefaultMethodKeyProvider implements MethodKeyProvider {

    @Override
    public String getKey(Method method) {
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

}
