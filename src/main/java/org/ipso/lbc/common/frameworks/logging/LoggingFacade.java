/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.frameworks.logging;
import org.apache.logging.log4j.Logger;
/**
 * 信息：李倍存 创建于 2016/01/11 10:01。电邮 1174751315@qq.com。<br>
 * 说明：
 */
public class LoggingFacade {

/**************************************信息*****************************************/
    public static void info(String msg){
        Logger logger = LoggerFactory.getLogger(3);
        if(logger.isInfoEnabled()){
            logger.info(msg);
        }
    }
    public static void infoGeneral(String msg){
        Logger logger = LoggerFactory.getLogger("info.developer.general");
        if (logger.isInfoEnabled()){
            logger.info(msg);
        }
    }
/**************************************重要信息*****************************************/
    public static void infoImportant(String msg){
        Logger logger = LoggerFactory.getLogger("info.developer.important");
        if (logger.isInfoEnabled()){
            logger.info(msg);
        }

    }
/**************************************关键信息*****************************************/
    public static void infoVital(String msg){
        Logger logger = LoggerFactory.getLogger("info.developer.vital");
        if (logger.isInfoEnabled()){
            logger.info(msg);
        }

    }



    /**************************************面向用户的错误告警*****************************************/
    /** 面向用户的错误告警。
     * @param logger 日志记录仪。
     * @param msg 信息。
     */
    /** 面向用户的错误告警。
     * @param msg 信息。
     */
    public static void errorUser(String msg){
        Logger logger = LoggerFactory.getLogger(3);
        if(logger.isErrorEnabled()) {
            logger.error(msg);//通知用户：用户需要知道的信息，开发者也一定要知道。
        }
        logger = LoggerFactory.getLogger("error.user");
        if (logger.isErrorEnabled()){
            logger.error(msg);
        }
    }
    public static void errorUser(String msg, Throwable t){
        Logger logger = LoggerFactory.getLogger(3);
        if(logger.isErrorEnabled()) {
            logger.error(msg, t);
        }
        logger = LoggerFactory.getLogger("error.user");
        if (logger.isErrorEnabled()){
            logger.error(msg);
        }
    }

    /**************************************错误告警*****************************************/
    /** 错误告警。
     * @param logger 日志记录仪。
     * @param msg 信息。
     */
    /** 错误告警。
     * @param msg 信息。
     */
    public static void error(String msg){
        Logger logger = LoggerFactory.getLogger(3);
        if(logger.isErrorEnabled()) {
            logger.error(msg);
        }
    }

    /** 错误告警。
     * @param msg 信息。
     */
    public static void error(String msg, Throwable t){
        Logger logger = LoggerFactory.getLogger(3);
        if(logger.isErrorEnabled()) {
            logger.error(msg, t);
        }
    }
    public static void warn(String msg){
        Logger logger = LoggerFactory.getLogger(3);
        if(logger.isWarnEnabled()) {
            logger.warn(msg);
        }
    }
    public static void fatal(String msg){
        Logger logger = LoggerFactory.getLogger(3);
        if(logger.isFatalEnabled()) {
            logger.fatal(msg);
        }
    }



    /**************************************流程跟踪*****************************************/
    /** 常规流程跟踪。
     * @param msg 信息。
     */
    public static void trace(String msg){
        Logger logger = LoggerFactory.getLogger(3);
        if(logger.isTraceEnabled()) {
            logger.trace(msg);
        }
    }

    /*************************************调试信息*****************************************/
    public static void debug(String msg){
        Logger logger = LoggerFactory.getLogger(3);
        if(logger.isDebugEnabled()) {
            logger.debug(msg);
        }
    }


}
