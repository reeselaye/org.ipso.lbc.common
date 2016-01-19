/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.exception.handler;

/**
 * 李倍存 创建于 2015-04-05 21:19。电邮 1174751315@qq.com。
 */
public class ExceptionHandler {

//    public void handleWithoutMailing(Exception ex,Logger logger,String prefix){
//        logger.info(prefix);
//        logger.error(prefix+"\n"+ex.getMessage());
//    }
//    public void handleWithoutMailing(Exception ex){
//        handleWithoutMailing(ex,Logging.instance().createLogger(),"");
//    }
//    public void handleWithoutMailing(Exception ex,String prefix){
//        handleWithoutMailing(ex,Logging.instance().createLogger(),prefix);
//    }
//
//    public void handle(Boolean mail,Boolean log){
//
//    }
//


    public ExceptionHandler() {
    }

    public void handle(Throwable e) {
        //DO NOTHING
    }


}
