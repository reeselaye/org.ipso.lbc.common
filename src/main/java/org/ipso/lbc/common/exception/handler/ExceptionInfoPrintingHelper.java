/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.exception.handler;

import org.ipso.lbc.common.frameworks.logging.Printing;

/**
 * 李倍存 创建于 2015-05-03 17:58。电邮 1174751315@qq.com。
 */
public class ExceptionInfoPrintingHelper {

    public static String getStackTraceInfo(Throwable throwable){
        String info=throwable.getClass().getSimpleName()+"\n";
        info+=throwable.getMessage()+"\n";
        StackTraceElement[] traceElements = throwable.getStackTrace();
        for (int i = 0; i < traceElements.length; i++) {
            info += "WHEN "+traceElements[i].toString() + "\n";
        }

        info+="[起因如下=========>]";
        count=0;
        info+=getCause(throwable);
        Printing.INSTANCE.print(info);
        return info;
    }

    private static Integer count=0;
    private static String getCause(Throwable throwable){
        String info="";
        Throwable cause=throwable.getCause();
        if (cause!=null){
            count++;
            String sps=spaces(count);
            info+="[起因 "+count+"]";
            info+=cause.getClass().getSimpleName()+"\n";
            info+=cause.getMessage()+"\n";
            StackTraceElement[] traceElements = cause.getStackTrace();
            for (int i = 0; i < traceElements.length; i++) {
                info += sps+"C:WHEN "+traceElements[i].toString() + "\n";
            }
            info+=getCause(cause);
        }else {
            return "[<=========起因如上]";
        }
        return info;
    }

    private static String spaces(Integer n){
        String sp="";
        n=n*4;
        for (int i=0;i<n;i++){
            sp+=(" ");
        }
        return sp;
    }
}
