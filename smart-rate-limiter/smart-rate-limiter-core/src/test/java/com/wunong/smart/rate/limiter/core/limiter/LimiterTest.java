package com.wunong.smart.rate.limiter.core.limiter;

import com.wunong.smart.rate.limiter.core.factory.RateLimiterFactory;
import com.wunong.smart.rate.limiter.core.factory.impl.DefaultRateLimiterFactory;
import com.wunong.smart.rate.limiter.core.support.GuavaRateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author created by zealot.zt
 */
public class LimiterTest {

    private static ExecutorService service = Executors.newFixedThreadPool(10);

    private static RateLimiterFactory factory = new DefaultRateLimiterFactory();

    public static final String TEST_PREFIX_KEY = "test_";

    private static final int KEY_SIZE = 3;

    static {
        for (int i = 0; i < KEY_SIZE; i++) {
            factory.register(getKey(i), new GuavaRateLimiter(10));
        }
    }

    private static String getKey(int i) {
        return TEST_PREFIX_KEY + i;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            service.submit(new LimierTask(factory, getKey(i % KEY_SIZE)));
        }
    }

}
