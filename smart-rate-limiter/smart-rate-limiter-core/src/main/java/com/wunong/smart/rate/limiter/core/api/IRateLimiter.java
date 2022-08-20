package com.wunong.smart.rate.limiter.core.api;

/**
 * 限制器
 *
 * @author created by zealot.zt
 */
public interface IRateLimiter {

    /**
     * 尝试获取指定数量的许可
     *
     * @param permits
     * @return
     */
    boolean tryAcquire(int permits);

    /**
     * 尝试获取许可
     *
     * @return
     */
    boolean tryAcquire();

    /**
     * 获取配置的qps
     *
     * @return
     */
    int getQps();

    /**
     * 是否为降级
     * 1. 即对应qps设置为0
     *
     * @return
     */
    default boolean isDegraded() {
        return getQps() <= 0;
    }

}
