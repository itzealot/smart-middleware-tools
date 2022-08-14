package com.wunong.smart.rate.limiter.core.exception;

import com.wunong.smart.domain.platform.exception.IError;
import com.wunong.smart.domain.platform.exception.IErrorLevel;
import com.wunong.smart.domain.platform.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

/**
 * 降级异常
 *
 * @author created by zealot.zt
 */
public class DegradedException extends ServiceException {

    /**
     * 降级错误码
     */
    public static final String DEGRADED_ERROR_CODE = "DEGRADED_ERROR";

    /**
     * 降级错误信息
     */
    public static final IError DEGRADED_ERROR = IError.of(DEGRADED_ERROR_CODE, "亲，请求失败请稍后重试", IErrorLevel.IGNORE);

    public DegradedException() {
        super(DEGRADED_ERROR);
    }

    public DegradedException(String errorInfo) {
        super(newDegradeError(errorInfo));
    }

    /**
     * 构建降级错误
     *
     * @param errorInfo
     * @return
     */
    private static IError newDegradeError(String errorInfo) {
        if (StringUtils.isBlank(errorInfo)) {
            return DEGRADED_ERROR;
        } else {
            return IError.of(DEGRADED_ERROR_CODE, errorInfo, IErrorLevel.IGNORE);
        }
    }

}
