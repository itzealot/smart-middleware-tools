package com.wunong.smart.rate.limiter.core.callback;

import com.wunong.smart.rate.limiter.core.config.LimiterData;
import com.wunong.smart.rate.limiter.core.exception.DegradedException;

/**
 * 降级后的回调方法
 *
 * @author created by zealot.zt
 */
public interface DegradedCallback {

    /**
     * 默认降级回调方法
     */
    DegradedCallback DEFAULT_CALLBACK = new DegradedCallback() {
        @Override
        public void callback(String key) {
            throw new DegradedException();
        }

        @Override
        public void callback(LimiterData data) {
            throw new DegradedException(data.getTips());
        }
    };

    /**
     * 被降级后的回调
     *
     * @param key 回调标识
     */
    void callback(String key) throws DegradedException;

    /**
     * 被降级后的回调
     *
     * @param data
     */
    void callback(LimiterData data) throws DegradedException;

}
