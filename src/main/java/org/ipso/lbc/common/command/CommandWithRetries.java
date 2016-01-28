/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.command;

import org.ipso.lbc.common.utils.ObjectUtils;

import static org.ipso.lbc.common.frameworks.logging.LoggingFacade.*;

/**
 * 信息：李倍存 创建于 2015/7/8 12:01。电邮 1174751315@qq.com。<br>
 * 说明：
 */
public class CommandWithRetries extends BasicCommand {
    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public CommandWithRetries() {
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof CommandWithRetries)) return false;
        if (!super.equals(o)) return false;

        CommandWithRetries that = (CommandWithRetries) o;

        if (!failTimes.equals(that.failTimes)) return false;
        if (!promptMessage.equals(that.promptMessage)) return false;
        if (!retryCount.equals(that.retryCount)) return false;

        return true;
    }
    public String toString(){
        return "{" +  getPromptMessage() + "," + super.toString() +"}" ;
    }
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + retryCount.hashCode();
        result = 31 * result + promptMessage.hashCode();
        result = 31 * result + failTimes.hashCode();
        return result;
    }

    public String getPromptMessage() {
        return promptMessage;
    }

    public void setPromptMessage(String promptMessage) {
        this.promptMessage = promptMessage;
    }

    public CommandWithRetries(IReceiver receiver, Integer retryCount, String promptMessage) {
        super(receiver);
        this.promptMessage = promptMessage + "with receiver info{" + receiver.info()+ "}";
        this.retryCount = retryCount;
    }

    public CommandWithRetries(IReceiver receiver, ICommandPostHandler postHandler, Integer retryCount,  String promptMessage) {
        super(receiver, postHandler);
        this.promptMessage = promptMessage;
        this.retryCount = retryCount;
    }

    public CommandWithRetries(IReceiver receiver, ICommandPreHandler preHandler, Integer retryCount,  String promptMessage) {
        super(receiver, preHandler);
        this.promptMessage = promptMessage;
        this.retryCount = retryCount;
    }

    public CommandWithRetries(IReceiver receiver, ICommandPreHandler preHandler, ICommandPostHandler postHandler, Integer retryCount, String promptMessage) {
        super(receiver, preHandler, postHandler);
        this.promptMessage = promptMessage;
        this.retryCount = retryCount;
    }



    private Integer retryCount;

    public Integer getRetryCount() {
        return retryCount;
    }

    public Integer getFailTimes() {
        return failTimes;
    }

    private String promptMessage;
    private Integer failTimes;

    @Override
    public Object       exec(Object params) {
        failTimes = 0;
        Object o = null;
        if (preHandler != null){
            preHandler.handle(this);
        }
        Exception failException = null ;
        String mission = (ObjectUtils.getHash4(this) + promptMessage );
        debug("Command {" + mission + "} starting.");
        do {
            try {
                o = once(receiver,params);
            } catch (Exception e) {
                failException = e;
            }
            if (failException != null) {
                failTimes++;
            }
            else {
                info("Command {" + mission + "} success.");
                break;
            }

       }while (failTimes <= retryCount);

        if(isFinallyFailed()){
            // logging
            if (failException != null && postHandler!= null){//due to exception and there's handler
                error(mission + "is finally failed, but there's a post handler{" +postHandler.toString() +  "} which might deal with it.", failException);
            } else if (failException != null && postHandler == null){
                error(mission + "is finally failed, and no handler is configured.", failException);
            } else if (failException == null && postHandler != null){
                warn(mission + " is finally failed because {" + reason() + "}, but there's a post handler{" +postHandler.toString() +  "} which might deal with it.");
            } else if (failException ==null && postHandler == null){
                warn(mission + " is finally failed because {" + reason() + "}, and no handler is configured.");
            }
        }

        if (postHandler != null){
            postHandler.handle(this);
        }
        return o;

    }
    public Boolean isFinallyFailed(){
        return failTimes > retryCount;
    }
    protected Object    reason(){
        return (retryCount>0?"there are " + retryCount + " retries failed.":"no retry is configured.");
    }
    protected Object    once(IReceiver receiver,Object params){
        if (receiver == null){
            warn(msgIfReceiverNull());
            return null;
        }
        return receiver.action(params);
    }




}
