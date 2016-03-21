/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package org.ipso.lbc.common.db.dao;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.ipso.lbc.common.exception.handler.ExceptionInfoPrintingHelper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建：2015/1/24 21:23
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 * 说明：该类为全能DAO，包含了所有数据访问的较低层操作，在Hibernate之上。<br>
 *      该类仅可产生数个实例，每一个数据库对应一个{@link SuperDAO}实例。
 */
public class SuperDAO {
    private SessionFactory sessionFactory;
    private Session defaultSession;

    public static SuperDAO create(SuperDAOConfiguration configuration){
        return new SuperDAO(configuration);
    }

    /** 通过给定配置对象生成SuperDAO实例。
     * @param configuration 给定配置对象
     */
    public SuperDAO(SuperDAOConfiguration configuration) {
        this(configuration.getConfiguration().buildSessionFactory());
    }
    /** 通过一个会话工厂构造一个SuperDAO。
     * @param sessionFactory 会话工厂
     */
    private SuperDAO(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public Session getSession() {
        return defaultSession;
    }
    private void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
        this.defaultSession = getSessionFactory().openSession();
    }






    /**
     * 在数据库中插入一个对象。
     * @param o 将要插入的对象。
     */
    public void insert(Object o) {
        Session session = defaultSession;
        Transaction t = session.beginTransaction();
        session.clear();
        session.save(o);
        t.commit();
    }

    /** 若数据库不存在特定对象，则插入一个对象；若数据库已存在特定对象，则更新它。
     * @param o 将要插入或更新的对象。
     */
    public void insertOrUpdate(Object o) {
        Session session = defaultSession;
        Transaction t = session.beginTransaction();
        session.clear();
        session.saveOrUpdate(o);
        t.commit();
    }

    /**
     * 在数据库中检索一个对象。
     * @param aClass 待检索的对象的类型。
     * @param pk     待检索的主键。
     * @return 主键所对应的数据表行所构成的对象图。
     */
    public Object query(Class aClass, Serializable pk) {
        Session session = defaultSession;
        Object o = session.get(aClass, pk);
        return o;
    }

    /**
     * 查询一个数据库表的所有对象。
     * @param aClass 待检索对象的类型。
     * @return 待检索对象类型所对应的数据表的所有行所构成的对象图的链表。
     */
    public List query(Class aClass) {
        List list = null;
        Session session = defaultSession;
        Transaction t = session.beginTransaction();
        Query q = session.createQuery("from " + aClass.getName());
        list = q.list();
        t.commit();
        return list;
    }

    /**
     * 更新一个数据库对象。
     * @param o 用于替换数据库原有对象的新对象。
     */
    public void update(Object o) {
        Session session = defaultSession;
        Transaction t = session.beginTransaction();
        session.update(o);
        t.commit();
    }

    /** 删除一个数据库表的所有对象。
     * @param aClass 待删除对象的类型。
     */
    public void delete(Class aClass) {
        Session session = defaultSession;
        Transaction t = session.beginTransaction();
        Query q = session.createQuery("delete " + aClass.getName());
        q.executeUpdate();
        t.commit();
    }

    /**
     * 在数据库中删除一个对象。
     * @param aClass 待删除对象的类型。
     * @param pk     待删除对象的主键。
     */
    public void delete(Class aClass, Serializable pk) {
        Session session = defaultSession;
        Transaction t = session.beginTransaction();
        session.delete(query(aClass, pk));
        t.commit();
    }



    public List query(String hql,Object ... params){
        return hqlExecuteQuery(hql, params);
    }
    /** 在数据库中直接通过HQL检索对象。
     * @param hql HQL语句。
     * @param params HQL位置参数表。
     * @return HQL检索结果。
     */
    public List hqlExecuteQuery(String hql,Object ... params){
        Session defaultSession = this.getSession();
        Query q = defaultSession.createQuery(hql);
        this.attachParameters(q, params);
        return q.list();
    }


    public void excuteUpdate(String sql, Object ... params){
        sqlExecuteUpdate(sql, params);
    }
    public void sqlExecuteUpdate(String sql, Object ... params){
        Session defaultSession = this.getSession();
        Transaction t = defaultSession.beginTransaction();
        SQLQuery query= defaultSession.createSQLQuery(sql);
        this.attachParameters(query, params);
        query.executeUpdate();
        t.commit();
    }

    public List excuteQuery(String sql, Object ... params){
        return sqlExecuteQuery(sql, params);
    }
    public List sqlExecuteQuery(String sql, Object ... params){
        Session defaultSession = this.getSession();
        SQLQuery query= defaultSession.createSQLQuery(sql);
        this.attachParameters(query, params);
        return query.list();
    }

    private void attachParameters(Query query, Object ... parameters){
        for (int i = 0; i < parameters.length; i++) {
            query.setParameter(i,parameters[i]);
        }
    }
}
