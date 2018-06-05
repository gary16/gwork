
package com.gwork.common.logging.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.gwork.common.logging.enumeration.LogLevelEnum;
import com.gwork.common.logging.enumeration.LogTriggerEnum;
import com.gwork.common.logging.service.LoggingAdapter;
import com.gwork.common.logging.service.impl.CommonLogging;

/**
 * 类概要
 * <p>
 * 日志拦截注解 包括 日志等级 日志触发 和 日志处理类
 * </p>
 * @author jialiang02.chen
 * @since 0.0.1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ActionLog {

    LogLevelEnum logLevel() default LogLevelEnum.Info;

    Class<? extends LoggingAdapter> loggingAdapter() default CommonLogging.class;

    LogTriggerEnum logTrigger() default LogTriggerEnum.AroundCall;
}
