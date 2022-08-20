package com.wunong.smart.rate.limiter.core.exception;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 限流异常
 *
 * @author created by zealot.zt
 */
public class LimitedException extends RuntimeException {

    /**
     * 限流错误码
     */
    public static final String LIMITED_ERROR_CODE = "LIMITED_ERROR";

    /**
     * 限流错误信息
     */
    public static final String LIMITED_ERROR_INFO = "亲，请求繁忙请稍后重试";

    /**
     * 错误码
     */
    @Getter
    private String errorCode;

    public LimitedException() {
        super(LIMITED_ERROR_INFO);
        this.errorCode = LIMITED_ERROR_CODE;
    }

    public LimitedException(String errorInfo) {
        super(getLimitedErrorInfo(errorInfo));
        this.errorCode = LIMITED_ERROR_CODE;
    }

    /**
     * 构建降级错误
     *
     * @param errorInfo
     * @return
     */
    private static String getLimitedErrorInfo(String errorInfo) {
        if (StringUtils.isBlank(errorInfo)) {
            return LIMITED_ERROR_INFO;
        } else {
            return errorInfo;
        }
    }

}
