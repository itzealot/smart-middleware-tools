package com.wunong.smart.rate.limiter.core.support;

import com.google.common.util.concurrent.RateLimiter;
import com.wunong.smart.rate.limiter.core.api.IRateLimiter;
import lombok.Data;

import java.util.Objects;

/**
 * Guava限流器
 *
 * @author created by zealot.zt
 */
@Data
public class GuavaRateLimiter implements IRateLimiter {

    /**
     * 限流器
     */
    private final RateLimiter rateLimiter;

    /**
     * qps值
     */
    private final int qps;

    public GuavaRateLimiter(int qps) {
        if (qps < 0) {
            throw new IllegalArgumentException("非法的限流值:" + qps);
        }
        this.qps = qps;
        // 若设置的大于0，则创建限流器
        if (qps > 0) {
            this.rateLimiter = RateLimiter.create(qps);
        } else {
            this.rateLimiter = null;
        }
    }

    public static GuavaRateLimiter create(int qps) {
        return new GuavaRateLimiter(qps);
    }

    /**
     * 尝试获取许可
     *
     * @param permits
     * @return
     */
    @Override
    public boolean tryAcquire(int permits) {
        if (isDegraded()) {
            return false;
        }

        if (Objects.isNull(rateLimiter)) {
            return false;
        }

        return rateLimiter.tryAcquire(permits);
    }

    /**
     * 尝试获取许可
     *
     * @return
     */
    @Override
    public boolean tryAcquire() {
        return tryAcquire(1);
    }

    @Override
    public int getQps() {
        return qps;
    }

}
