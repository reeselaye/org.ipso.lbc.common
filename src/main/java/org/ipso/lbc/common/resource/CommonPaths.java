/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package org.ipso.lbc.common.resource;

import org.ipso.lbc.common.config.Configuration;
import org.ipso.lbc.common.utils.ResourcePathHelper;
import org.ipso.lbc.common.exception.NotImplementedException;

import java.util.Timer;
import java.util.TimerTask;

import static org.ipso.lbc.common.frameworks.logging.LoggingFacade.*;

/**
 * Created by LBC on 2015/2/11.
 */
public class CommonPaths {
    private static boolean warnWorkingDirNotSet = false;
    private static boolean infoRoot = false;
    public static  String getContextRoot() {
        String root = Configuration.INSTANCE.getConfigurationEnsureReturn("app.context.workingDir");
        if (root.equals("")){
            if (!warnWorkingDirNotSet){
                warnWorkingDirNotSet = true;
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.err.println("The app.context.workingDir is not correctly set!\nPlease check the application.properties file.");
                    }
                },5000);
            }
        }
        if (root.matches("[a-zA-Z]:(.*)")) {//so it is a absolute path.
            return root;
        }
        if (root.startsWith("classpath:")) {// so it is given depends on the classpath.
            throw new NotImplementedException("We are sorry that this feature is not supported yet.");
        }
        //consider it as a relative path.
        final String t = ResourcePathHelper.getAbsolutePath(root);
        if (!infoRoot){
            infoRoot = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("The working directory root is set to: " + t);
                }
            },5000);
        }
        return t;
    }
    public static  String getContextLog(){return getContextRoot() + "log/";}
    public static  String getContextCache(){return getContextRoot() + "cache/";}
    public static  String getContextTemp(){return getContextRoot() + "temp/";}
    public static  String getContextConfig(){return getContextRoot() + "config/";}
    public static  String getContextData(){return getContextRoot() + "data/";}

}
