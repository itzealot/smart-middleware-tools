package com.wunong.smart.rate.limiter.core.limiter;

import com.wunong.smart.rate.limiter.core.factory.RateLimiterFactory;
import com.wunong.smart.rate.limiter.core.factory.impl.DefaultRateLimiterFactory;
import com.wunong.smart.rate.limiter.core.support.GuavaRateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author created by zealot.zt
 */
public class AllLimitedTest {

    private static final int POOL_SIZE = 2;
    private static ExecutorService service = Executors.newFixedThreadPool(POOL_SIZE);
    private static RateLimiterFactory factory = new DefaultRateLimiterFactory();

    public static final int qps = 0;
    public static final String TEST_PREFIX_KEY = "test_";
    private static final int KEY_SIZE = 4;

    static {
        for (int i = 0; i < KEY_SIZE; i++) {
            factory.register(getKey(i), GuavaRateLimiter.create(qps));
        }
    }

    private static String getKey(int i) {
        return TEST_PREFIX_KEY + i;
    }

    public static void main(String[] args) {
        for (int i = 0; i < POOL_SIZE; i++) {
            service.submit(new LimierTask(factory, getKey(i % KEY_SIZE)));
        }
    }

}
