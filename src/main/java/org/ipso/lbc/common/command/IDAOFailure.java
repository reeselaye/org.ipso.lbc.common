/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.command;

import org.ipso.lbc.common.exception.NotImplementedException;

import java.util.List;

/**
 * 信息：李倍存 创建于 2016/01/08 20:45。电邮 1174751315@qq.com。<br>
 * 说明：
 */
public interface IDAOFailure {
    void insertOrUpdate(Failure failure);
    List<Failure> all();
    void delete(String type, String params);
    Failure query(String type, String params);
    Failure query(String id);

    public class Default implements IDAOFailure {

        private static NotImplementedException notImplementedException = new NotImplementedException("若要使用失败命令持久化功能，请提供DAOFailure的实现。");

        @Override
        public void insertOrUpdate(Failure failure) {
            throw notImplementedException;
        }

        @Override
        public List<Failure> all() {
            throw notImplementedException;
        }

        @Override
        public void delete(String type, String params) {
            throw notImplementedException;
        }

        @Override
        public Failure query(String type, String params) {
            throw notImplementedException;
        }

        @Override
        public Failure query(String id) {
            throw notImplementedException;
        }
    }
}
