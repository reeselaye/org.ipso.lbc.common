package org.ipso.lbc.common.boot;

import org.ipso.lbc.common.frameworks.logging.LoggingFacade;

/**
 * Created by John Resse (2016/6/2 16:05), contact mister.resse@outlook.com.<br>
 */
public class SystemUtils {
    public static void logFatalAndExit(String msg, Integer exitCode){
        LoggingFacade.fatal(msg);
        System.exit(exitCode);
    }

    public static void logFatalAndExit(String msg, Throwable t, Integer exitCode){
        LoggingFacade.fatal(msg, t);
        System.exit(exitCode);
    }
}
