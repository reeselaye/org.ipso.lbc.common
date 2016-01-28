/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.command;

import java.io.Serializable;

/**
 * 信息：李倍存 创建于 2015/7/8 12:06。电邮 1174751315@qq.com。<br>
 * 说明：
 */
public abstract class Receiver implements Serializable,IReceiver{
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Receiver)) return false;

        Receiver receiver = (Receiver) o;

        if (logEnable != receiver.logEnable) return false;

        return true;
    }
    public Object info(){
        return this.toString();
    }
    @Override
    public int hashCode() {
        return (logEnable ? 1 : 0);
    }

    protected boolean logEnable = true;

    public void setLogEnable(boolean logEnable) {
        this.logEnable = logEnable;
    }


    public Object[]         getParams(){
        return new Object[]{0};
    }
    public String getClassName(){
        return this.getClass().getSimpleName();
    }

    public boolean isLogEnable() {
        return logEnable;
    }

    public static class Default extends Receiver{
        @Override
        public boolean equals(Object o) {
            return true;
        }
        @Override
        public Object action(Object params) {
            return null;
        }
    }
}
