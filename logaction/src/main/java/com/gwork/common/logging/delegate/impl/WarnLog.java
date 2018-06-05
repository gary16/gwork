
package com.gwork.common.logging.delegate.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gwork.common.logging.delegate.LoggingDelegate;

/**
 * 类概要
 * <p>
 * 警告日志输出代理
 * </p>
 * @author jialiang02.chen
 * @since 0.0.1
 */
@Component
public class WarnLog implements LoggingDelegate {

    private static Logger logger = LoggerFactory.getLogger(WarnLog.class);

    @Override
    public void doLog(String msg) {
        // TODO Auto-generated method stub
        logger.warn(msg);
    }

}
