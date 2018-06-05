
package com.gwork.common.logging.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.gwork.common.logging.delegate.LoggingDelegate;
import com.gwork.common.logging.service.LoggingAdapter;

/**
 * 类概要
 * <p>
 * spring工具类从spring上下文中获取logbean
 * </p>
 * @author jialiang02.chen
 * @since 0.0.1
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> srcClz) {
        T t = applicationContext.getBean(srcClz);
        return t;
    }

    public static LoggingDelegate getLevelDelegate(Class<? extends LoggingDelegate> srcClz) {
        LoggingDelegate loggingDelegate = applicationContext.getBean(srcClz);
        return loggingDelegate;
    }

    public static LoggingAdapter getLevelAdapter(Class<? extends LoggingAdapter> srcClz) {
        LoggingAdapter loggingAdapter = applicationContext.getBean(srcClz);
        return loggingAdapter;
    }
}
