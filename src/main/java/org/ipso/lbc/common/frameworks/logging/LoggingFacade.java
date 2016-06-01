/*
 * Copyright the original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ipso.lbc.common.frameworks.logging;

import org.apache.logging.log4j.Logger;

/**
 * Create by John Resse (2016/01/11 10:01), contact mister.resse@outlook.com.<br>
 */
public class LoggingFacade {
    public static void info(String msg) {
        Logger logger = LoggerFactory.getLogger(3);
        if (logger.isInfoEnabled()) {
            logger.info(msg);
        }
    }
    /** 错误告警。
     * @param logger 日志记录仪。
     * @param msg 信息。
     */
    /**
     * 错误告警。
     *
     * @param msg 信息。
     */
    public static void error(String msg) {
        Logger logger = LoggerFactory.getLogger(3);
        if (logger.isErrorEnabled()) {
            logger.error(msg);
        }
    }

    /**
     * 错误告警。
     *
     * @param msg 信息。
     */
    public static void error(String msg, Throwable t) {
        Logger logger = LoggerFactory.getLogger(3);
        if (logger.isErrorEnabled()) {
            logger.error(msg, t);
        }
    }

    public static void warn(String msg) {
        Logger logger = LoggerFactory.getLogger(3);
        if (logger.isWarnEnabled()) {
            logger.warn(msg);
        }
    }

    public static void fatal(String msg) {
        Logger logger = LoggerFactory.getLogger(3);
        if (logger.isFatalEnabled()) {
            logger.fatal(msg);
        }
    }

    /**
     * 常规流程跟踪。
     *
     * @param msg 信息。
     */
    public static void trace(String msg) {
        Logger logger = LoggerFactory.getLogger(3);
        if (logger.isTraceEnabled()) {
            logger.trace(msg);
        }
    }

    public static void debug(String msg) {
        Logger logger = LoggerFactory.getLogger(3);
        if (logger.isDebugEnabled()) {
            logger.debug(msg);
        }
    }


}
