package com.wunong.smart.rate.limiter.core.api;

import com.wunong.smart.rate.limiter.core.config.InvokeEvent;

/**
 * 对应的限流解析器
 *
 * @author created by zealot.zt
 */
public interface KeyResolver {

    /**
     * 解析限流key
     *
     * @param event
     * @return
     */
    String resolve(InvokeEvent event);

}
