/*
 * Copyright the original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.ipso.lbc.common.db.dao;

import org.hibernate.*;
import org.ipso.lbc.common.frameworks.logging.LoggingFacade;

import java.io.Serializable;
import java.lang.*;
import java.util.List;

/**
 * 创建：2015/1/24 21:23
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 * 说明：该类为全能DAO，包含了所有数据访问的较低层操作，在Hibernate之上。<br>
 * 该类仅可产生数个实例，每一个数据库对应一个{@link SuperDAO}实例。
 */
public class SuperDAO {
    private SessionFactory sessionFactory;
    private Session defaultSession;

    /**
     * 通过给定配置对象生成SuperDAO实例。
     *
     * @param configuration 给定配置对象
     */
    private SuperDAO(SuperDAOConfiguration configuration) {
        this(configuration.getConfiguration().buildSessionFactory());
    }

    /**
     * 通过一个会话工厂构造一个SuperDAO。
     *
     * @param sessionFactory 会话工厂
     */
    private SuperDAO(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    public static SuperDAO create(SuperDAOConfiguration configuration) {
        SuperDAO superDAO = null;
        try {
            LoggingFacade.debug("Creating the SuperDAO instance...");
            superDAO = new SuperDAO(configuration);
            LoggingFacade.info("Successfully to create the SuperDAO instance{everything of database works fine}. [" + configuration.getConfiguration().toString() + "]");
        } catch (HibernateException e){
            LoggingFacade.fatal("The application is terminated because we cannot access the specified database.", e);
            System.exit(-1);
        }

        return superDAO;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.defaultSession = getSessionFactory().openSession();
    }

    public Session getSession() {
        return defaultSession;
    }

    /**
     * 在数据库中插入一个对象。
     *
     * @param o 将要插入的对象。
     */
    public void insert(Object o) {
        Session session = getSession();
        Transaction t = session.beginTransaction();
        try {
            session.clear();
            session.save(o);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            throw e;
        }
    }

    /**
     * 若数据库不存在特定对象，则插入一个对象；若数据库已存在特定对象，则更新它。
     *
     * @param o 将要插入或更新的对象。
     */
    public void insertOrUpdate(Object o) {
        Session session = getSession();
        Transaction t = session.beginTransaction();
        try {
            session.clear();
            session.saveOrUpdate(o);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            throw e;
        }
    }

    /**
     * 在数据库中检索一个对象。
     *
     * @param aClass 待检索的对象的类型。
     * @param pk     待检索的主键。
     * @return 主键所对应的数据表行所构成的对象图。
     */
    public Object query(Class aClass, Serializable pk) {
        Session session = getSession();
        Object o = session.get(aClass, pk);
        return o;
    }

    /**
     * 查询一个数据库表的所有对象。
     *
     * @param aClass 待检索对象的类型。
     * @return 待检索对象类型所对应的数据表的所有行所构成的对象图的链表。
     */
    public List query(Class aClass) {
        List list = null;
        Session session = getSession();
        Transaction t = session.beginTransaction();
        try {
            Query q = session.createQuery("from " + aClass.getName());
            list = q.list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
            throw e;
        }
        return list;
    }

    /**
     * 更新一个数据库对象。
     *
     * @param o 用于替换数据库原有对象的新对象。
     */
    public void update(Object o) {
        Session session = getSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(o);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            throw e;
        }
    }

    /**
     * 删除一个数据库表的所有对象。
     *
     * @param aClass 待删除对象的类型。
     */
    public void delete(Class aClass) {
        Session session = getSession();
        Transaction t = session.beginTransaction();
        try {
            Query q = session.createQuery("delete " + aClass.getName());
            q.executeUpdate();
            t.commit();
        } catch (Exception e) {
            t.rollback();
            throw e;
        }
    }

    /**
     * 在数据库中删除一个对象。
     *
     * @param aClass 待删除对象的类型。
     * @param pk     待删除对象的主键。
     */
    public void delete(Class aClass, Serializable pk) {
        Session session = getSession();
        Transaction t = session.beginTransaction();
        try {
            session.delete(query(aClass, pk));
            t.commit();
        } catch (Exception e) {
            t.rollback();
            throw e;
        }
    }

    public List hqlQuery(String hql, Object... params) {
        return hqlExecuteQuery(hql, params);
    }

    @Deprecated
    public List query(String hql, Object... params) {
        return hqlExecuteQuery(hql, params);
    }

    /**
     * 在数据库中直接通过HQL检索对象。
     *
     * @param hql    HQL语句。
     * @param params HQL位置参数表。
     * @return HQL检索结果。
     */
    @Deprecated
    public List hqlExecuteQuery(String hql, Object... params) {
        Session session = getSession();
        Query q = session.createQuery(hql);
        this.attachParameters(q, params);
        return q.list();
    }

    public void sqlUpdate(String sql, Object... params) {
        sqlExecuteUpdate(sql, params);
    }

    @Deprecated
    public void excuteUpdate(String sql, Object... params) {
        sqlExecuteUpdate(sql, params);
    }

    @Deprecated
    public void sqlExecuteUpdate(String sql, Object... params) {
        Session session = getSession();
        Transaction t = session.beginTransaction();
        try {
            SQLQuery query = session.createSQLQuery(sql);
            this.attachParameters(query, params);
            query.executeUpdate();
            t.commit();
        } catch (Exception e) {
            t.rollback();
            throw e;
        }
    }

    public List sqlQuery(String sql, Object... params) {
        return sqlExecuteQuery(sql, params);
    }

    @Deprecated
    public List excuteQuery(String sql, Object... params) {
        return sqlExecuteQuery(sql, params);
    }

    @Deprecated
    public List sqlExecuteQuery(String sql, Object... params) {
        Session session = getSession();
        SQLQuery query = session.createSQLQuery(sql);
        this.attachParameters(query, params);
        return query.list();
    }

    private void attachParameters(Query query, Object... parameters) {
        for (int i = 0; i < parameters.length; i++) {
            query.setParameter(i, parameters[i]);
        }
    }
}
