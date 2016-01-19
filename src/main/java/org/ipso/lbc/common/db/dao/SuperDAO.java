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

    /**
     * 实例库。用一个哈希表保存该类的所有实例。
     */
    private static Map<String, SuperDAO> instances = new HashMap<String, SuperDAO>();
    /**
     * 会话工厂。
     */
    private SessionFactory sessionFactory;
    /**
     * 使用的默认会话。
     */
    private Session defaultSession;

    /** 通过一个会话工厂构造一个SuperDAO。
     * @param sessionFactory 会话工厂
     */
    public SuperDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.defaultSession = this.sessionFactory.openSession();
    }

    /** 通过一个会话构造一个SuperDAO。
     * @param session 会话
     */
    public SuperDAO(Session session){
        this.defaultSession=session;
    }
    public SuperDAO(String configurationFileName, String url){
        this(new Configuration().configure(configurationFileName).setProperty("hibernate.connection.url", url).buildSessionFactory());
    }
    public SuperDAO(String configurationFileName){
        this(new Configuration().configure(configurationFileName).buildSessionFactory());
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public Session getSession() {
        return defaultSession;
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

    /** 在数据库中直接通过HQL检索对象。
     * @param hql HQL语句。
     * @param params HQL位置参数表。
     * @return HQL检索结果。
     */
    public List query(String hql,Object ... params){
        Session defaultSession = this.getSession();
        Query q = defaultSession.createQuery(hql);
        for (int i = 0; i < params.length; i++) {
            q.setParameter(i,params[i]);
        }
        return q.list();
    }

    public void excuteUpdate(String sql, Object ... params){

        try {
            Session defaultSession = this.getSession();
            Transaction t = defaultSession.beginTransaction();
            SQLQuery query= defaultSession.createSQLQuery(sql);
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i,params[i]);
            }
            query.executeUpdate();
            t.commit();
        }catch (Exception e){
            String str = ExceptionInfoPrintingHelper.getStackTraceInfo(e);
        }



    }
    public List excuteQuery(String sql, Object ... params){

        Session defaultSession = this.getSession();

        SQLQuery query= defaultSession.createSQLQuery(sql);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i,params[i]);
        }

        return query.list();
    }

}
