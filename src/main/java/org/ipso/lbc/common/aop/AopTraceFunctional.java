/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.aop;

import org.apache.logging.log4j.Logger;
import org.ipso.lbc.common.frameworks.logging.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * Created by LBC on 2015-04-12.
 */
public class AopTraceFunctional implements AfterReturningAdvice, ThrowsAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        success.info((o1).toString());
    }

    public void afterThrowing(Method m, Object[] os, Object target, Throwable throwable) {
        fail.error(throwable.getMessage());
    }

    private static Logger success = LoggerFactory.getLogger("【预测成功】");
    private static Logger fail = LoggerFactory.getLogger("【预测失败】");
}
