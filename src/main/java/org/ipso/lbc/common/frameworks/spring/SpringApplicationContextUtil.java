/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.frameworks.spring;

import org.ipso.lbc.common.config.Configuration;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.ipso.lbc.common.frameworks.logging.LoggingFacade.*;


/**
 * 信息：李倍存 创建于 2015-07-01 12:24。电邮 1174751315@qq.com。<br>
 * 说明：
 */
/* Loads the spring application context automatically by ApplicationContextAware, if Appli cationContextAware is not active, then loads the context from
 * the properties (see @link{Configuration}), that is ${app.context.frameworks.spring.contextConfigLocation}.
 *
 * If you config the context with ${DEFAULT_PROPERTY_NAME}, please ensure that no spring*.xml file specify the ApplicationContextAware.
 *
 */
public class SpringApplicationContextUtil implements ApplicationContextAware {

    public SpringApplicationContextUtil() {
    }
    private static final String DEFAULT_PROPERTY_NAME = "app.context.frameworks.spring.contextConfigLocation";
    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context=applicationContext;
        if (context!=null){
            info("Spring container is successfully initialized with ApplicationContextAware.");
        } else{
            fatal("Spring container is failed to be initialized, and the application is shutting down.");
            System.exit(-1);
        }
    }

    public static ApplicationContext getContext(){
        if (context == null){
            String prefix = "The ApplicationContextAware is inactive, ";
            debug(prefix + "trying to load the spring context from specified files.");
            String configurations = Configuration.INSTANCE.getConfigurationEnsureReturn(DEFAULT_PROPERTY_NAME);
            if (configurations.equals("")){
                fatal(prefix + "and no spring configuration is specified, please provide " + DEFAULT_PROPERTY_NAME + " in any .properties file.");
                System.exit(-1);
            }

            String t1="";
            String[] t2 = configurations.split(",");
            for (int i = 0; i < t2.length; i++) {
                t1 += t2[i];
            }
            info(prefix + "but everything would works fine because we load the spring context from " + t1);
            setContext(new ClassPathXmlApplicationContext(t2));
        }
        return context;
    }

    public static Object getBean(String s){
        return getContext().getBean(s);
    }
    private static void setContext(ApplicationContext  c){
        context = c;
    }

}
