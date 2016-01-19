/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.command;

import static org.ipso.lbc.common.frameworks.logging.LoggingFacade.error;
import static org.ipso.lbc.common.frameworks.logging.LoggingFacade.info;

/**
 * 信息：李倍存 创建于 2016/01/09 21:33。电邮 1174751315@qq.com。<br>
 * 说明：
 */
public class CommandFailureRecorder implements ICommandPostHandler{
    protected static Integer MAX_FAILURE_TIMES_ALLOWED = 5;
    public CommandFailureRecorder() {
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }

    @Override
    public Object handle(BasicCommand command) {

        if (command instanceof CommandWithRetries){
            CommandWithRetries commandWithRetries = (CommandWithRetries) command;
            CommandSerializer.INSTANCE.delete(commandWithRetries);
            if (command.isFinallyFailed()){
                if (commandWithRetries.getFlag() > MAX_FAILURE_TIMES_ALLOWED){
                    error("Command{" +command.toString() + "} is cleared after "  + MAX_FAILURE_TIMES_ALLOWED + " failures.");
                } else {
                    commandWithRetries.setFlag(commandWithRetries.getFlag() + 1);
                    commandWithRetries.setPromptMessage("#" + commandWithRetries.getPromptMessage());
                    CommandSerializer.INSTANCE.serialize(commandWithRetries);
                }
            } else {
                if (command.getFlag()>0){
                    info("Command{" +command.toString() + "} is cleared after "  + command.getFlag() + " retries and finally success.");
                }
            }
        }
        return null;
    }

}
