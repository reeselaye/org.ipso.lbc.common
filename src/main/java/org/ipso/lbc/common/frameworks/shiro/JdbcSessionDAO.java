/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.frameworks.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.ipso.lbc.common.db.dao.SuperDAO;

import java.io.Serializable;
import java.util.Collection;

/**
 * 李倍存 创建于 2015-05-08 16:21。电邮 1174751315@qq.com。
 */
public class JdbcSessionDAO extends AbstractSessionDAO {
    public JdbcSessionDAO() {
    }

    public void setSuperDAO(SuperDAO superDAO) {
        this.superDAO = superDAO;
    }

    private SuperDAO superDAO;
    @Override
    protected Serializable doCreate(Session session) {
        MySession mySession=new MySession();
        Serializable id=generateSessionId(session);
        assignSessionId(session,id);
        mySession.sessionTo(session);
        superDAO.insertOrUpdate(mySession);
        return session.getId();
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        MySession mySession=(MySession)superDAO.query(MySession.class,serializable);
        if (mySession==null){
            return null;
        }
        return mySession.toSession();
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        MySession mySession=new MySession();
        mySession.sessionTo(session);
        superDAO.insertOrUpdate(mySession);
    }

    @Override
    public void delete(Session session) {
        MySession mySession=new MySession();
        mySession.sessionTo(session);
        superDAO.delete(MySession.class, mySession.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
//        LinkedList<Session> sessions=new LinkedList<Session>();
//        List my=superDAO.query(MySession.class);
//        for (int i = 0; i < my.size(); i++) {
//            sessions.add(((MySession)my.get(i)).toSession());
//        }
        return null;
    }
}
