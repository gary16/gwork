
package com.gwork.common.logging.delegate.factory;

import java.util.HashMap;
import java.util.Map;

import com.gwork.common.logging.delegate.LoggingDelegate;
import com.gwork.common.logging.enumeration.LogLevelEnum;
import com.gwork.common.logging.utils.SpringContextUtils;

/**
 * 类概要
 * <p>
 * 日志代理工厂，获取日志代理类
 * </p>
 * @author jialiang02.chen
 * @since 0.0.1
 */
public class LoggingDelegateFactory {

    private static Map<String, LogLevelEnum> levelMap = new HashMap<String, LogLevelEnum>();

    static {
        levelMap.put(LogLevelEnum.Info.toString(), LogLevelEnum.Info);
        levelMap.put(LogLevelEnum.Debug.toString(), LogLevelEnum.Debug);
        levelMap.put(LogLevelEnum.Error.toString(), LogLevelEnum.Error);
        levelMap.put(LogLevelEnum.Warn.toString(), LogLevelEnum.Warn);
    }

    public static LoggingDelegate generateDelegate(LogLevelEnum logLevelEnum) {

        Class<? extends LoggingDelegate> srcClz = levelMap.get(logLevelEnum.toString()).getHandleClz();

        LoggingDelegate loggingDelegate = SpringContextUtils.getLevelDelegate(srcClz);

        return loggingDelegate;
    }

}
