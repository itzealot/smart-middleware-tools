package com.wunong.smart.rate.limiter.starter.handler;

import com.wunong.smart.rate.limiter.core.config.InvokeEvent;
import com.wunong.smart.rate.limiter.starter.annotation.Limiter;

/**
 * 调用事件限流
 *
 * @author created by zealot.zt
 */
public interface InvokeEventHandler {

    /**
     * 根据调用事件限流
     *
     * @param invokeEvent
     * @param limiter
     */
    void tryLimit(InvokeEvent invokeEvent, Limiter limiter);

}
