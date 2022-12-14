package com.wunong.smart.rate.limiter.core.config;

import com.wunong.smart.rate.limiter.core.enums.LimitTypeEnum;
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

    /**
     * 限流类型
     */
    private LimitTypeEnum limitTypeEnum;

    public static LimiterData create(String key, String tips, LimitTypeEnum sourceEnum) {
        LimiterData data = new LimiterData();
        data.setKey(key);
        data.setTips(tips);
        data.setLimitTypeEnum(sourceEnum);
        return data;
    }

    public static LimiterData create(String key, LimitTypeEnum sourceEnum) {
        LimiterData data = new LimiterData();
        data.setKey(key);
        data.setLimitTypeEnum(sourceEnum);
        return data;
    }

}
