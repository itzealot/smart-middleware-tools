package com.wunong.smart.rate.limiter.starter.aspect;

import com.wunong.smart.rate.limiter.core.config.InvokeEvent;
import com.wunong.smart.rate.limiter.starter.annotation.Limiter;
import com.wunong.smart.rate.limiter.starter.handler.InvokeEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author create by zealot.zt
 */
@Aspect
@Slf4j
@Component(LimiterAspect.BEAN_NAME)
public class LimiterAspect {

    public static final String BEAN_NAME = "com.wunong.smart.rate.limiter.starter.aspect.LimiterAspect";

    @Resource
    private InvokeEventHandler invokeEventHandler;

    @Around("@annotation(limiter)")
    public Object around(ProceedingJoinPoint joinPoint, Limiter limiter) throws Throwable {
        // 前置限流
        invokeEventHandler.tryLimit(create(joinPoint), limiter);

        // 调用原有切面方法
        return joinPoint.proceed();
    }

    /**
     * 创建事件
     *
     * @param joinPoint
     * @return
     */
    protected InvokeEvent create(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        return InvokeEvent.builder()
                .clazz(joinPoint.getTarget().getClass())
                .methodName(signature.getName())
                .args(joinPoint.getArgs())
                .method(signature.getMethod()).build();
    }

}
