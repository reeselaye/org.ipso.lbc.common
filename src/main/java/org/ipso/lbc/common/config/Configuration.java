/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.config;

import org.ipso.lbc.common.utils.file.FileSystemAndResourceUtils;

import java.io.*;
import java.util.*;

import static org.ipso.lbc.common.frameworks.logging.LoggingFacade.*;

/**
 * 信息：李倍存 创建于 2016/01/15 20:21。电邮 1174751315@qq.com。<br>
 * 说明：
 */
public class Configuration {
    private static final String DEFAULT_PROPERTY_VALUE = "";
    public static Configuration INSTANCE = new Configuration();
    private Properties properties;


    private List<String> info=new LinkedList<String>(), debug=new LinkedList<String>(), warn=new LinkedList<String>();

    private Configuration() {
        properties = new Properties();
        List<InputStream> iss = FileSystemAndResourceUtils.getAllResources("application.properties");
        info.add(iss.size() + " application.properties found, loading all of them ...");
        for (int i = 0; i < iss.size(); i++) {
            InputStream is = iss.get(i);
            try {
                debug.add("Loading properties from " + is.toString());
                properties.load(iss.get(i));
                info.add("Successfully load properties from " + is.toString());
            } catch (IOException e) {
                warn.add("Cannot load properties from " + is.toString());
            }
        }
        FileSystemAndResourceUtils.closeAll(iss);

        String kvs="";
        Object[] keys = properties.stringPropertyNames().toArray();
        for (int i = 0; i < keys.length; i++) {
            Object key = keys[i];
            kvs += key + " - " + properties.getProperty(key.toString()) +"\n";
        }
        info.add("The following properties is loaded:\n" + kvs);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < debug.size(); i++) {
                    debug(debug.get(i));
                }
                for (int i = 0; i < info.size(); i++) {
                    info(info.get(i));
                }
                for (int i = 0; i < warn.size(); i++) {
                    warn(warn.get(i));
                }

            }
        }, 5000);
    }

    public String getConfigurationEnsureReturn(String name){
        String value = properties.getProperty(name);
        if (value.isEmpty()){
            warn("Attempt to get an empty property{key:" + name +"}, the default value " + DEFAULT_PROPERTY_VALUE +  " is returned.");
            return DEFAULT_PROPERTY_VALUE;
        }
        return value;
    }
    public String getConfiguration(String name){
        return properties.getProperty(name);
    }
}
