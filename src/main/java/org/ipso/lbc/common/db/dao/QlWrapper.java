package org.ipso.lbc.common.db.dao;

import org.hibernate.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by John Resse (2016/6/3 21:46), contact mister.resse@outlook.com.<br>
 */
public class QlWrapper<Entity> {
    protected SessionFactory sessionFactory;
    protected Session defaultSession;

    public QlWrapper(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getSession() {
        return defaultSession;
    }

    public void sqlExecuteUpdate(String sql, Object... params) {
        Session defaultSession = this.getSession();
        Transaction t = defaultSession.beginTransaction();
        SQLQuery query = defaultSession.createSQLQuery(sql);
        this.attachParameters(query, params);
        query.executeUpdate();
        t.commit();
    }

    /**
     * 在数据库中直接通过HQL检索对象。
     *
     * @param hql    HQL语句。
     * @param params HQL位置参数表。
     * @return HQL检索结果。
     */
    public List<Entity> hqlExecuteQuery(String hql, Object... params) {
        Session defaultSession = this.getSession();
        Query q = defaultSession.createQuery(hql);
        this.attachParameters(q, params);
        return convert(q.list());
    }

    public List<Entity> sqlExecuteQuery(String sql, Object... params) {
        Session defaultSession = this.getSession();
        SQLQuery query = defaultSession.createSQLQuery(sql);
        this.attachParameters(query, params);
        return convert(query.list());
    }

    private void attachParameters(Query query, Object... parameters) {
        for (int i = 0; i < parameters.length; i++) {
            query.setParameter(i, parameters[i]);
        }
    }

    private void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.defaultSession = getSessionFactory().openSession();
    }

    private List<Entity> convert(List list) {
        List<Entity> entities = new LinkedList<Entity>();
        for (Object o : list) {
            entities.add((Entity) o);
        }
        avoidIndexOutOfBoundExceptionWhenList_get_0(entities);
        return entities;
    }

    private void avoidIndexOutOfBoundExceptionWhenList_get_0(List<Entity> list){
        if (list.size() == 0){
            list.add(null);
        }
    }
}
