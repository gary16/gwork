
package com.gwork.common.logging.service;

import java.lang.reflect.Method;

import com.gwork.common.logging.delegate.LoggingDelegate;

/**
 * 类概要
 * <p>
 * 日志打印适配器
 * </p>
 * @author jialiang02.chen
 * @since 0.0.1
 */
public interface LoggingAdapter {

    public void preLog(LoggingDelegate loggingDelegate, Method method, Object param);

    public void postLog(LoggingDelegate loggingDelegate, Method method, Object param);

}
