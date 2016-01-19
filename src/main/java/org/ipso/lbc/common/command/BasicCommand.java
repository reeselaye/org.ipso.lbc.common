/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.command;

import java.io.Serializable;

/**
 * 信息：李倍存 创建于 2015/7/8 12:05。电邮 1174751315@qq.com。<br>
 * 说明：
 */
public class BasicCommand implements Serializable {
    protected Receiver              receiver;
    protected ICommandPreHandler    preHandler;
    protected ICommandPostHandler   postHandler;
    private   Integer               flag = 0;
    public String toString(){
        return "{" + this.hashCode() + "," + getInfo() + "," + getStringParams() + "}";
    }


    public Integer getFlag() {
        return flag;
    }
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public              BasicCommand(Receiver receiver) {
        this(receiver, null, null);

    }
    public              BasicCommand(Receiver receiver, ICommandPreHandler preHandler, ICommandPostHandler postHandler){
        setReceiver(receiver);
        setPreHandler(preHandler);
        setPostHandler(postHandler);
    }
    public              BasicCommand(Receiver receiver, ICommandPreHandler preHandler){
        this(receiver, preHandler, null);
    }
    public              BasicCommand(Receiver receiver, ICommandPostHandler postHandler){
        this(receiver, null, postHandler);
    }

    public void         setPostHandler(ICommandPostHandler postHandler) {
        this.postHandler = postHandler;
    }
    public void         setPreHandler(ICommandPreHandler preHandler) {
        this.preHandler = preHandler;
    }
    public void         setReceiver(Receiver receiver){
        this.receiver=receiver;
    }

    public Receiver     getReceiver() {
        return receiver;
    }
    public Object[]     getParams(){
        return receiver.getParams();
    }
    public String       getStringParams() {
        return params(getParams());
    }
    public String       getParam(Integer index){
        String[] params = getStringParams().split(",");
        return params[index];
    }
    public String       getInfo(){
        return receiver.getClassName();
    }


    public Object       exec(Object param){
        preHandler.handle(this);
        Object o = receiver.action(param);
        postHandler.handle(this);
        return o ;
    }
    public Boolean isFinallyFailed(){
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicCommand)) return false;

        BasicCommand command = (BasicCommand) o;

        if (!flag.equals(command.flag)) return false;
        if (!postHandler.equals(command.postHandler)) return false;
        if (!preHandler.equals(command.preHandler)) return false;
        if (!receiver.equals(command.receiver)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = receiver.hashCode();
        if (preHandler != null)
            result = 31 * result + preHandler.hashCode();
        if (postHandler != null)
            result = 31 * result + postHandler.hashCode();
        result = 31 * result + flag.hashCode();
        return result;
    }

    public ICommandPostHandler getPostHandler() {
        return postHandler;
    }

    public ICommandPreHandler getPreHandler() {
        return preHandler;
    }


    private String params(Object[] params){
        String s = "";
        for (int i = 0; i < params.length; i++) {
            s += params[i] + ",";
        }
        return s;
    }

}
