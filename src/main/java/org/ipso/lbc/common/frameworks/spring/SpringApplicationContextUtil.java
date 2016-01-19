/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.frameworks.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import static org.ipso.lbc.common.frameworks.logging.LoggingFacade.fatal;
import static org.ipso.lbc.common.frameworks.logging.LoggingFacade.info;


/**
 * 信息：李倍存 创建于 2015-07-01 12:24。电邮 1174751315@qq.com。<br>
 * 说明：
 */
public class SpringApplicationContextUtil implements ApplicationContextAware {
    public SpringApplicationContextUtil() {
    }
    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context=applicationContext;
        if (context!=null){
            info("Spring container is successfully initialized.");
        } else{
            fatal("Spring container is failed to be initialized, and the application is shutting down.");
            System.exit(-1);
        }
    }

    public static ApplicationContext getContext(){
        return context;
    }

    public static Object getBean(String s){
        return context.getBean(s);
    }


}
