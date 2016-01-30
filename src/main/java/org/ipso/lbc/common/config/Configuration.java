/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.config;

import org.ipso.lbc.common.utils.file.FileSystemAndResourceUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.ipso.lbc.common.frameworks.logging.LoggingFacade.*;

/**
 * 信息：李倍存 创建于 2016/01/15 20:21。电邮 1174751315@qq.com。<br>
 * 说明：
 */
/*
 * Load all .properties files at the root of CLASSPATH, and then attach them to System Properties.
 */
public class Configuration {
    private static final String DEFAULT_PROPERTY_VALUE = "";
    public static Configuration INSTANCE = new Configuration();
    private Properties properties;


    private List<String> info=new LinkedList<String>(), debug=new LinkedList<String>(), warn=new LinkedList<String>();

    private Configuration() {


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


        properties = new Properties();
        List<InputStream> iss = FileSystemAndResourceUtils.getAllResources("classpath*:*.properties");
        if (iss.size() == 0){
            warn.add("No .properties file is found, which is probably a problem.");
            return;
        }
        info.add(iss.size() + " .properties found, loading all of them ...");
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
        info.add("Attaching the loaded properties to System Properties ...");
        System.getProperties().putAll(properties);
        FileSystemAndResourceUtils.closeAll(iss);

        String kvs="";
        Object[] keys = getAllGatheredProperties().stringPropertyNames().toArray();
        for (int i = 0; i < keys.length; i++) {
            Object key = keys[i];
            kvs += key + " - " + getAllGatheredProperties().getProperty(key.toString()) +"\n";
        }
        debug.add("The following properties is loaded:\n" + kvs);


    }

    public String getConfigurationEnsureReturn(String name){
        String value = getAllGatheredProperties().getProperty(name);
        if (value==null ||  value.isEmpty()){
            warn("Attempt to get an empty property{key:" + name +"}, the default value " + DEFAULT_PROPERTY_VALUE +  " is returned.");
            return DEFAULT_PROPERTY_VALUE;
        }
        return value;
    }
    public String getConfiguration(String name){
        return getAllGatheredProperties().getProperty(name);
    }

    private Properties getAllGatheredProperties(){
        return System.getProperties();
    }
}
