/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.config;

import org.ipso.lbc.common.resource.CommonPaths;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import static org.ipso.lbc.common.frameworks.logging.LoggingFacade.*;

/**
 * 信息：李倍存 创建于 2016/01/15 20:21。电邮 1174751315@qq.com。<br>
 * 说明：
 */
public class Configuration {
    private static final String DEFAULT_PROPERTY_VALUE = "default";
    public static Configuration INSTANCE = new Configuration();


    private Properties properties;
    private Configuration() {
        String cfg = CommonPaths.WEB_CONTENT_ROOT()+"application.properties";
        String prefix = "Default configuration is loaded, because we can't ";
        properties = new Properties();
        try {
            debug("Starting to load the configuration from {" + cfg +"}.");
            properties.load(new InputStreamReader(new FileInputStream(cfg), "UTF-8"));

        } catch (FileNotFoundException e) {
            warn(prefix + "locate the configuration file {" + cfg +"}.");
            loadDefaultProperties(properties);
        } catch (IOException e) {
            warn(prefix + "can't read the configuration file {" + cfg+"}.");
            loadDefaultProperties(properties);
        }
        info("The application is configured as in {" + getConfiguration("environment")+"} environment");
    }

    private void loadDefaultProperties(Properties properties){

        properties.setProperty("environment", "development");
        properties.setProperty("version", "1.1");
        properties.setProperty("organization","iPso");

    }
    public String getConfiguration(String name){
        String value = properties.getProperty(name);
        if (value.isEmpty()){
            warn("Attempt to get an empty property{key:" + name +"}, the default value " + DEFAULT_PROPERTY_VALUE +  " is returned.");
            return DEFAULT_PROPERTY_VALUE;
        }
        return value;
    }
}
