/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.frameworks.logging;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.ReflectionUtil;
import org.ipso.lbc.common.exception.AppUnCheckException;
import org.ipso.lbc.common.resource.CommonPaths;

import java.util.HashMap;
import java.util.Map;

/**
 * 信息：李倍存 创建于 2015-03-25 11:41。电邮 1174751315@qq.com。
 * 说明：该类为工厂类/单例，依赖于log4j框架，用于统一封装创建Logger的过程。
 */
public class LoggerFactory {
    /**
     * log4j配置文件路径。
     */
    public static void ConfigureLog4j(){
        /*配置log4j。*/
//        log4j.logs系统变量在log.properties文件被引用。
        System.setProperty("log4j.logs", CommonPaths.getContextLog());
        System.setProperty("log4j.directory", System.getProperty("log4j.logs"));
        try {

        } catch (Exception e) {
            throw new AppUnCheckException("找不到log4j配置文件 - " + "", e);
        }
    }
    public static Logger getLogger() {
        return LogManager.getLogger(2);
    }
    public static Logger getLogger(Integer callerClassDepth){
        return LogManager.getLogger((Class) ReflectionUtil.getCallerClass(callerClassDepth));
    }
    public static Logger getLogger(String loggerName) {
        return LogManager.getLogger(loggerName);
    }






    public static class OfName {
        public static String VITAL = "vital.";
        public static String PROPERTIES = "properties";
        public static String ACTION = "action";
        public static String GENERAL = "general.";
        public static OfName INSTANCE = new OfName();

        private Map<String,String> namesMapping0;
        private Map<String,String> namesMapping1;
        private OfName(){
            namesMapping0 = new HashMap<String, String>();
            namesMapping0.put("vital", "关键");
            namesMapping0.put("general","常规");
            namesMapping0.put("user","用户");

            namesMapping1 = new HashMap<String, String>();
            namesMapping1.put("properties", "属性");
            namesMapping1.put("action", "活动");
            namesMapping1.put("retry", "重试");
            namesMapping1.put("warning", "警告");
            namesMapping1.put("error", "错误告警");
        }

        /** 根据代号获取日志记录仪名称。
         * @param string 代号。
         * @return 相应的日志记录仪名称，若找不到代号对应的名称，则直接返回默认值。
         */
        public String get(String string){
            String[] names = string.split("\\.");
            if (names.length != 2){
                throw new AppUnCheckException("不符合默认日志记录仪的命名规范");
            }
            String s = namesMapping0.get(names[0]);
            if (s != null){
                names[0] = s;
            }
            s = namesMapping1.get(names[1]);
            if (s != null){
                names[1] = s;
            }
            return names[0] + "." + names[1];
        }
    }

}
