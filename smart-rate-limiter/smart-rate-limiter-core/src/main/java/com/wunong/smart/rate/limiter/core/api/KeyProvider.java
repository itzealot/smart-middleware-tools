package com.wunong.smart.rate.limiter.core.api;

/**
 * 限流key提供者
 *
 * @author created by zealot.zt
 */
public interface KeyProvider {

    /**
     * 获取限流key
     *
     * @return
     */
    String getKey();

}
