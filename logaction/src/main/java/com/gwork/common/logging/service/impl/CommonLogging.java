
package com.gwork.common.logging.service.impl;

import java.lang.reflect.Method;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.gwork.common.logging.delegate.LoggingDelegate;
import com.gwork.common.logging.service.LoggingAdapter;

/**
 * 类概要
 * <p>
 * 日志适配器的公共日志类
 * </p>
 * @author jialiang02.chen
 * @since 0.0.1
 */
@Component
public class CommonLogging implements LoggingAdapter {

    @Override
    public void preLog(LoggingDelegate loggingDelegate, Method method, Object param) {
        loggingDelegate.doLog(
                method.getDeclaringClass() + "  method:" + method.getName() + "  args:" + JSON.toJSONString(param));
    }

    @Override
    public void postLog(LoggingDelegate loggingDelegate, Method method, Object returnObj) {
        loggingDelegate.doLog(method.getDeclaringClass() + "  method:" + method.getName() + "  return:"
                + JSON.toJSONString(returnObj));
    }

}
