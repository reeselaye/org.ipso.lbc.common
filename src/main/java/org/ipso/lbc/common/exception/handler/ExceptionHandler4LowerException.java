/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.exception.handler;

import org.apache.logging.log4j.Logger;
import org.ipso.lbc.common.frameworks.logging.LoggerFactory;

/**
 * Created by LBC on 2015-04-06.
 */
public class ExceptionHandler4LowerException implements IExceptionHandler {

    private String loggerName;

    public ExceptionHandler4LowerException(String loggerName) {
        this.loggerName = loggerName;
    }

    public ExceptionHandler4LowerException() {
    }

    @Override
    public String handle(Throwable throwable, String prefix) {
        defaultHandling(throwable, LoggerFactory.getLogger(loggerName), prefix);
        return prefix + throwable.getMessage();
    }


    public static void defaultHandling(Throwable exception, Logger logger, String msgPrefix) {
        new ExceptionLogger(new ExceptionMailer(new ExceptionHandler()), logger, msgPrefix).handle(exception);
    }

    public static void defaultHandling(Throwable exception, String msgPrefix) {
        defaultHandling(exception, LoggerFactory.getLogger(), msgPrefix);
    }

    public static void defaultHandling(Throwable exception, Logger logger) {
        defaultHandling(exception, logger, "【未指定异常提示】");
    }

    public static void defaultHandling(Throwable e) {
        defaultHandling(e, LoggerFactory.getLogger(), "【未指定异常提示】");
    }
}
