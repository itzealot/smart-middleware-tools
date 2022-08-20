package com.wunong.smart.rate.limiter.core.limiter;

import com.wunong.smart.rate.limiter.core.exception.DegradedException;
import com.wunong.smart.rate.limiter.core.exception.LimitedException;
import com.wunong.smart.rate.limiter.core.factory.RateLimiterFactory;

/**
 * @author created by zealot.zt
 */
public class LimierTask implements Runnable {

    private String key;
    private RateLimiterFactory factory;

    public LimierTask(RateLimiterFactory factory, String key) {
        this.factory = factory;
        this.key = key;
    }

    @Override
    public void run() {
        while (true) {
            try {
                factory.tryLimit(key);
            } catch (LimitedException e) {
                synchronized (LimierTask.class) {
                    System.out.println("current " + Thread.currentThread().getName() + " limited on " + key + ", msg:" + e.getErrorCode());
                }
            } catch (DegradedException e) {
                synchronized (LimierTask.class) {
                    System.out.println("current " + Thread.currentThread().getName() + " degraded on " + key + "msg:" + e.getErrorCode());
                }
            }

            try {
                Thread.sleep(200L);
            } catch (InterruptedException e) {
                // do nothing
            }
        }
    }

}
