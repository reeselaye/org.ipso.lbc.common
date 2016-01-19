/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.command;
import org.ipso.lbc.common.condition.ICondition;
/**
 * 信息：李倍存 创建于 2015-08-16 21:43。电邮 1174751315@qq.com。<br>
 * 说明：
 */
public class CommandRetryWhenConditionTrue extends CommandWithRetries {

    private ICondition condition = new DefaultCondition();

    public void addCondition(ICondition condition){
        this.condition = condition;
    }

    public CommandRetryWhenConditionTrue(Receiver receiver, ICommandPostHandler postHandler, Integer retryCount, String promptMessage ) {
        super(receiver, postHandler, retryCount, promptMessage);
    }

    public CommandRetryWhenConditionTrue(Receiver receiver, ICommandPreHandler preHandler, ICommandPostHandler postHandler, Integer retryCount, String promptMessage ) {
        super(receiver, preHandler, postHandler, retryCount, promptMessage);
    }

    public CommandRetryWhenConditionTrue(Receiver receiver, ICommandPreHandler preHandler, Integer retryCount, String promptMessage ) {
        super(receiver, preHandler, retryCount, promptMessage);
    }

    public CommandRetryWhenConditionTrue(Receiver receiver, Integer retryCount, String promptMessage) {
        super(receiver, retryCount, promptMessage);
    }

    @Override
    public Boolean isFinallyFailed() {
        return condition.decide();
    }

    @Override
    protected Object reason() {
        return condition.info();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandRetryWhenConditionTrue)) return false;
        if (!super.equals(o)) return false;

        CommandRetryWhenConditionTrue that = (CommandRetryWhenConditionTrue) o;

        if (!condition.equals(that.condition)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + condition.hashCode();
        return result;
    }

    private class DefaultCondition implements ICondition{
        @Override
        public boolean equals(Object o) {
            return true;
        }

        @Override
        public Boolean decide() {
            return true;
        }
        @Override
        public Object info(){
            return "默认失败条件。该提示不应出现，若您看到该提示，请检查程序逻辑。";
        }
    }
}
