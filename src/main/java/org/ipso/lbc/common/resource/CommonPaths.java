/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package org.ipso.lbc.common.resource;

import org.ipso.lbc.common.utils.ResourcePathHelper;

/**
 * Created by LBC on 2015/2/11.
 */
public class CommonPaths {
    public static String ROOT="";



    public static  String WEB_CONTENT_ROOT() {
        return ResourcePathHelper.getAbsolutePath(ROOT);//WEB根目录
    }
    public static  String WEB_CONTENT_LOG_ROOT(){return WEB_CONTENT_ROOT() + "LOG/";}
    private static String WEB_CONTENT_NAME(){return WEB_CONTENT_ROOT().substring(WEB_CONTENT_ROOT().substring(WEB_CONTENT_ROOT().length() - 1).lastIndexOf("/") + 1);}
    public static  String WEB_CONTENT_CACHES(){return WEB_CONTENT_ROOT() + "CACHES/";}//WEB的CACHES目录，用于保存缓存记录
    public static  String WEB_CONTENT_TEMP(){return ResourcePathHelper.getAbsolutePath(ROOT+"../../TEMP/");}//WEB的TEMP目录，用于缓存数据或者其他
    public static  String WEB_CONTENT_CONFIG(){return WEB_CONTENT_ROOT() + "CONFIG/";}//WEB的CONFIG目录，用于保存配置文件
    public static  String WEB_CONTENT_DATA(){
        return WEB_CONTENT_ROOT() + "DATA/";}//WEB的DATA目录，用于保存数据库相关文件
    public static String WEB_RELATIVE_PATH_OF(String content) {
        return WEB_CONTENT_NAME() + content;
    }
}
