
package com.gwork.common.logging.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.gwork.common.logging.annotation.ActionLog;
import com.gwork.common.logging.delegate.LoggingDelegate;
import com.gwork.common.logging.delegate.factory.LoggingDelegateFactory;
import com.gwork.common.logging.enumeration.LogTriggerEnum;
import com.gwork.common.logging.service.LoggingAdapter;
import com.gwork.common.logging.utils.SpringContextUtils;

/**
 * 类概要
 * <p>
 * 日志拦截器
 * </p>
 * @author jialiang02.chen
 * @since 0.0.1
 */
@Aspect
@Component
public class ActionLogAspect {

    @Around(value = " @annotation(alog) ")
    public Object doActionLog(ProceedingJoinPoint pjp, ActionLog alog) throws Throwable {

        LoggingAdapter loggingAdapter = SpringContextUtils.getLevelAdapter(alog.loggingAdapter());
        LoggingDelegate loggingDelegate = LoggingDelegateFactory.generateDelegate(alog.logLevel());
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();

        if ((alog.logTrigger() == LogTriggerEnum.AroundCall)
                || (alog.logTrigger() == LogTriggerEnum.BeforeCall)) {
            Object param = pjp.getArgs();
            loggingAdapter.preLog(loggingDelegate, method, param);
        }

        Object returnObj = pjp.proceed();

        if ((alog.logTrigger() == LogTriggerEnum.AroundCall) || (alog.logTrigger() == LogTriggerEnum.AfterCall)) {
            loggingAdapter.postLog(loggingDelegate, method, returnObj);
        }

        return returnObj;

    }

}
