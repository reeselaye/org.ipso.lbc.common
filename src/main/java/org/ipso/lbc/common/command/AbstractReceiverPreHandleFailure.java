/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.command;

import org.ipso.lbc.common.exception.handler.ExceptionInfoPrintingHelper;
import org.ipso.lbc.common.frameworks.logging.LoggerFactory;

import java.util.List;

/**
 * 信息：李倍存 创建于 2016/01/08 22:19。电邮 1174751315@qq.com。<br>
 * 说明：
 */
public abstract class AbstractReceiverPreHandleFailure extends AbstractReceiver implements ICommandPreHandler {

    @Override
    public boolean equals(Object o) {
        return true;
    }

    protected abstract void resolveFailure(Failure failure);
    @Override
    public Object handle(BasicCommand command) {
        List<Failure> failures = FailureRecorder.INSTANCE.getExistingFailures();
        for (int i = 0; i < failures.size(); i++) {
            Failure failure = failures.get(i);
            if (failure.getType().equals(command.getInfo())){
                try {
                    resolveFailure(failure);
                    FailureRecorder.INSTANCE.reportFailureResolved(failure);
                }catch (Exception e){
                    FailureRecorder.INSTANCE.reportFailureAgain(failure);
                    if (logEnable){
                        String ex = ExceptionInfoPrintingHelper.getStackTraceInfo(e);
                        LoggerFactory.getLogger(this.getClass().getSimpleName()).error(ex);
                        System.err.print(ex);
                    }
                }
            }
        }
        return null;
    }
}
