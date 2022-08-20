package com.wunong.smart.rate.limiter.core.config;

import com.wunong.smart.rate.limiter.core.api.KeyProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * 调用事件
 *
 * @author create by zealot.zt
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvokeEvent implements Serializable {

    /**
     * 对应类
     */
    private Class<?> clazz;

    /**
     * 方法
     */
    private String methodName;

    /**
     * 对应方法
     */
    private Method method;

    /**
     * 参数信息
     */
    private Object[] args;

    /**
     * 获取调用关键字
     *
     * @return
     */
    public String getInvokeKey() {
        return clazz.getSimpleName() + "." + methodName;
    }

    /**
     * 获取key解析器
     *
     * @return
     */
    public KeyProvider findKeyProvider() {
        if (ArrayUtils.isNotEmpty(args)) {
            return Stream.of(args).filter(arg -> arg instanceof KeyProvider)
                    .map(arg -> (KeyProvider) arg)
                    .findFirst().orElse(null);
        }

        return null;
    }

}
