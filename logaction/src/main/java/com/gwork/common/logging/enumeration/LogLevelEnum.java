
package com.gwork.common.logging.enumeration;

import com.gwork.common.logging.delegate.LoggingDelegate;
import com.gwork.common.logging.delegate.impl.DebugLog;
import com.gwork.common.logging.delegate.impl.ErrorLog;
import com.gwork.common.logging.delegate.impl.InfoLog;
import com.gwork.common.logging.delegate.impl.WarnLog;

/**
 * 类概要
 * <p>
 * 日志等级枚举，包含日志等级名称和对应处理class， 包括 信息 错误 调试 警告 等级
 * </p>
 * @author jialiang02.chen
 * @since 0.0.1
 */
public enum LogLevelEnum {

    Info("info", InfoLog.class), Error("error", ErrorLog.class), Debug("debug", DebugLog.class), Warn("warn",
            WarnLog.class);

    private String levelName;

    private Class<? extends LoggingDelegate> handleClz;

    private LogLevelEnum(String levelName, Class<? extends LoggingDelegate> handleClz) {
        this.levelName = levelName;
        this.handleClz = handleClz;
    }

    public Class<? extends LoggingDelegate> getHandleClz() {
        return this.handleClz;
    }

    @Override
    public String toString() {
        return this.levelName;
    }

}
