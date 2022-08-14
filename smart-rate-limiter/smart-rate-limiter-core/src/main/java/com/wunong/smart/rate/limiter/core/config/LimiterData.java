package com.wunong.smart.rate.limiter.core.config;

import lombok.Data;

/**
 * 限流配置数据
 *
 * @author created by zealot.zt
 */
@Data
public class LimiterData {

    /**
     * 限流关键词
     */
    private String key;

    /**
     * 限流提示
     */
    private String tips;

    public static LimiterData create(String key, String tips) {
        LimiterData data = new LimiterData();
        data.setKey(key);
        data.setTips(tips);
        return data;
    }

}
