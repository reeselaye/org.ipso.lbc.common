/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.command;

import java.util.List;

/**
 * 信息：李倍存 创建于 2016/01/08 16:29。电邮 1174751315@qq.com。<br>
 * 说明：
 */
public class FailureRecorder implements ICommandPostHandler {
    private boolean needToUpdate = true;
    private List<Failure> failures;

    public void setDao(IDAOFailure dao) {
        this.dao = dao;
    }

    private IDAOFailure dao = new IDAOFailure.Default();
    public static FailureRecorder INSTANCE = new FailureRecorder();

    private FailureRecorder() {
    }

    public List<Failure> getExistingFailures(){
        if (needToUpdate){
            failures = dao.all();
            needToUpdate = false;
        }
        return failures;
    }
    public void reportFailureResolved(Failure failure){
        dao.delete(failure.getType(), failure.getParams());
        needToUpdate = true;
    }
    public void reportFailureAgain(Failure failure){
        failure.reassignTime();
        reportFailure(failure);
    }
    private void reportFailure(Failure failure){
        dao.insertOrUpdate(failure);
        needToUpdate = true;
    }


    @Override
    public Object handle(BasicCommand command) {
        if (command.isFinallyFailed()){
            String params = "";
            Object[] objs = command.getParams();
            for (int i = 0; i < objs.length; i++) {
                params = objs[i] + ",";
            }
            Failure newItem = new Failure(params, command.getInfo(), "");
            reportFailure(newItem);
        }
        return null;
    }
}
