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
import java.io.Serializable;

/**
 * 创建：2015/1/24 21:23
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 */
public class SuperDAO<Entity> extends QlWrapper<Entity> {
    private Class entityClass;

    /**
     * 通过一个会话工厂构造一个SuperDAO。
     *
     * @param sessionFactory 会话工厂
     */
    public SuperDAO(SessionFactory sessionFactory, Class entityClass) {
        super(sessionFactory);
        setEntityClass(entityClass);
    }

    /**
     * 在数据库中插入一个对象。
     *
     * @param o 将要插入的对象。
     */
    public void insert(Entity o) {
        Session session = defaultSession;
        Transaction t = session.beginTransaction();
        session.clear();
        session.save(o);
        t.commit();
    }

    /**
     * 若数据库不存在特定对象，则插入一个对象；若数据库已存在特定对象，则更新它。
     *
     * @param o 将要插入或更新的对象。
     */
    public void insertOrUpdate(Entity o) {
        Session session = defaultSession;
        Transaction t = session.beginTransaction();
        session.clear();
        session.saveOrUpdate(o);
        t.commit();
    }

    /**
     * 更新一个数据库对象。
     *
     * @param o 用于替换数据库原有对象的新对象。
     */
    public void update(Entity o) {
        Session session = defaultSession;
        Transaction t = session.beginTransaction();
        session.update(o);
        t.commit();
    }

    /**
     * 删除一个数据库表的所有对象。
     */
    public void delete() {
        Session session = defaultSession;
        Transaction t = session.beginTransaction();
        Query q = session.createQuery("delete " + entityClass.getName());
        q.executeUpdate();
        t.commit();
    }

    /**
     * 在数据库中删除一个对象。
     *
     * @param pk 待删除对象的主键。
     */
    public void delete(Serializable pk) {
        Session session = defaultSession;
        Transaction t = session.beginTransaction();
        session.delete(query(pk));
        t.commit();
    }

    /**
     * 在数据库中检索一个对象。
     *
     * @param pk 待检索的主键。
     * @return 主键所对应的数据表行所构成的对象图。
     */
    public Entity query(Serializable pk) {
        Session session = defaultSession;
        Object o = session.get(entityClass, pk);
        return (Entity) o;
    }

    private void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }
}
