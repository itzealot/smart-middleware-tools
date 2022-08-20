package com.wunong.smart.rate.limiter.core.support;

import com.wunong.smart.rate.limiter.core.api.MethodKeyProvider;

import java.lang.reflect.Method;

/**
 * @author created by zealot.zt
 */
public class DefaultMethodKeyProvider implements MethodKeyProvider {

    @Override
    public String getKey(Method method) {
        // 方法标识
        StringBuilder builder = new StringBuilder(128)
                .append(method.getDeclaringClass().getName())
                .append(".")
                .append(method.getName());

        // 参数类型
        Class<?>[] classes = method.getParameterTypes();

        if (classes.length > 0) {
            builder.append("(");
            for (Class<?> clazz : classes) {
                builder.append(clazz.getSimpleName()).append(",");
            }
            builder.deleteCharAt(builder.length() - 1);
            return builder.append(")").toString();
        }

        return builder.toString();
    }

}
