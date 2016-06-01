/*
 * Copyright the original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ipso.lbc.common.frameworks.logging;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.ReflectionUtil;

/**
 * Create by John Resse (2015/3/25 11:41), contact mister.resse@outlook.com.<br>
 * 该类为工厂类/单例，依赖于log4j框架，用于统一封装创建Logger的过程。
 */
public class LoggerFactory {
    public static Logger getLogger() {
        return LogManager.getLogger(2);
    }

    public static Logger getLogger(Integer callerClassDepth) {
        return LogManager.getLogger((Class) ReflectionUtil.getCallerClass(callerClassDepth));
    }

    public static Logger getLogger(String loggerName) {
        return LogManager.getLogger(loggerName);
    }
}
