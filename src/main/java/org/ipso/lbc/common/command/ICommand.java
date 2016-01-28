package org.ipso.lbc.common.command;

/**
 * 信息：李倍存 创建于 2016/01/27 20:35。电邮 1174751315@qq.com。<br>
 * 说明：
 */
public interface ICommand {
    public IReceiver     getReceiver();
    public Object[]     getParams();
    public String       getStringParams();
    public String       getInfo();


    public Object       exec(Object param);
    public Boolean isFinallyFailed();

    public ICommandPostHandler getPostHandler();

    public ICommandPreHandler getPreHandler();
}
