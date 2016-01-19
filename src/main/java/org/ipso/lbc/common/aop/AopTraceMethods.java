/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.aop;

import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.ipso.lbc.common.frameworks.logging.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 李倍存 创建于 2015-04-12 14:19。电邮 1174751315@qq.com。
 */

@Aspect
public class AopTraceMethods implements MethodBeforeAdvice, AfterReturningAdvice{
    private static Logger methodTracingLogger = LoggerFactory.getLogger("aop.trace.exit");
    private static Logger methodenterTracingLogger = LoggerFactory.getLogger("aop.trace.enter");
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        String paras = "";
        for (int i = 0; i < objects.length; i++) {
            paras += objects[i].hashCode() + "\n";
        }
        String text = o1.getClass().getName() + "." + method.getName() + "参数" + paras + "\n返回  " + o + "\n";
        text+=new SimpleDateFormat("HH:mm:ss.SSS").format(new Date());
        methodTracingLogger.trace(text);
    }

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        methodenterTracingLogger.trace("进入  " + o.getClass().getName() + "." + method.getName()+"\n"+new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()));

    }
}
