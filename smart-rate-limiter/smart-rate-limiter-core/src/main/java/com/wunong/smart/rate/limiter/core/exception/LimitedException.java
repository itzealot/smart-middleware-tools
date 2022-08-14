package com.wunong.smart.rate.limiter.core.exception;

import com.wunong.smart.domain.platform.exception.IError;
import com.wunong.smart.domain.platform.exception.IErrorLevel;
import com.wunong.smart.domain.platform.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

/**
 * 限流异常
 *
 * @author created by zealot.zt
 */
public class LimitedException extends ServiceException {

    /**
     * 限流错误码
     */
    public static final String LIMITED_ERROR_CODE = "LIMITED_ERROR";

    /**
     * 限流错误信息
     */
    public static final IError LIMITED_ERROR = IError.of(LIMITED_ERROR_CODE, "亲，请求繁忙请稍后重试", IErrorLevel.IGNORE);

    public LimitedException() {
        super(LIMITED_ERROR);
    }

    public LimitedException(String errorInfo) {
        super(IError.of(LIMITED_ERROR_CODE, errorInfo, IErrorLevel.IGNORE));
    }

    /**
     * 构建降级错误
     *
     * @param errorInfo
     * @return
     */
    private static IError newLimitedError(String errorInfo) {
        if (StringUtils.isBlank(errorInfo)) {
            return LIMITED_ERROR;
        } else {
            return IError.of(LIMITED_ERROR_CODE, errorInfo, IErrorLevel.IGNORE);
        }
    }

}
