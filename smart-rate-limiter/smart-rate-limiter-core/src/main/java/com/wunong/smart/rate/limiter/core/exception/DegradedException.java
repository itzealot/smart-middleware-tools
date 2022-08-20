package com.wunong.smart.rate.limiter.core.exception;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 降级异常
 *
 * @author created by zealot.zt
 */
public class DegradedException extends RuntimeException {

    /**
     * 降级错误码
     */
    public static final String DEGRADED_ERROR_CODE = "DEGRADED_ERROR";

    /**
     * 错误信息
     */
    public static final String DEGRADED_ERROR_INFO = "亲，请求失败请稍后重试";

    /**
     * 错误码
     */
    @Getter
    private String errorCode;

    public DegradedException() {
        super(DEGRADED_ERROR_INFO);
        this.errorCode = DEGRADED_ERROR_CODE;
    }

    public DegradedException(String errorInfo) {
        super(getDegradeErrorInfo(errorInfo));
        this.errorCode = DEGRADED_ERROR_CODE;
    }

    /**
     * 获取降级错误信息
     *
     * @param errorInfo
     * @return
     */
    protected static String getDegradeErrorInfo(String errorInfo) {
        if (StringUtils.isBlank(errorInfo)) {
            return DEGRADED_ERROR_INFO;
        } else {
            return errorInfo;
        }
    }

}
