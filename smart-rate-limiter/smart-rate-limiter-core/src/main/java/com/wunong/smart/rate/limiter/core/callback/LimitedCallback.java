package com.wunong.smart.rate.limiter.core.callback;

import com.wunong.smart.rate.limiter.core.config.LimiterData;
import com.wunong.smart.rate.limiter.core.exception.LimitedException;

/**
 * 限流后的回调方法
 *
 * @author created by zealot.zt
 */
public interface LimitedCallback {

    /**
     * 默认限流回调方法
     */
    LimitedCallback DEFAULT_CALLBACK = new LimitedCallback() {
        @Override
        public void callback(String key) {
            throw new LimitedException();
        }

        @Override
        public void callback(LimiterData data) {
            throw new LimitedException(data.getTips());
        }
    };

    /**
     * 被限流后的回调
     *
     * @param key 回调标识
     */
    void callback(String key) throws LimitedException;

    /**
     * 被限流后的回调
     *
     * @param data
     */
    void callback(LimiterData data) throws LimitedException;

}
