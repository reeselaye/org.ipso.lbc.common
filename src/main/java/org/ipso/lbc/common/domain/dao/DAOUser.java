/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.domain.dao;

import org.ipso.lbc.common.adm.user.User;
import org.ipso.lbc.common.adm.user.UserUtils;
import org.ipso.lbc.common.ado.ElementPrintableLinkedList;
import org.ipso.lbc.common.db.dao.AbstractDAO;
import org.ipso.lbc.common.db.dao.SuperDAO;

import java.util.List;

/**
 * 信息：李倍存 创建于 2015-05-09 20:34。电邮 1174751315@qq.com。
 * 说明：该类负责对用户进行MD5哈希算法加密以及数据库访问。
 */
public class DAOUser extends AbstractDAO {
    public DAOUser(SuperDAO superDAO) {
        this.superDAO=superDAO;
    }



    /** 加密一个用户账户，并插入数据库。
     * @param user 未加密用户账户。
     */
    public void insert(User user){
        superDAO.insert(UserUtils.encript(user));
    }

    /** 以加密的形式，插入或更新一个用户账户。
     * @param user 未加密用户账户。
     */
    public void insertOrUpdate(User user){
        superDAO.insertOrUpdate(UserUtils.encript((user)));
    }
    public void delete(String name){
        superDAO.delete(User.class,name);
    }
    public User query(String name){
        return (User)superDAO.query(User.class,name);
    }

    public ElementPrintableLinkedList<User> query(){
        List list=superDAO.query("from User");
        ElementPrintableLinkedList<User> users=new ElementPrintableLinkedList<User>("unnamed");
        for (int i = 0; i < list.size(); i++) {
            users.add((User)list.get(i));
        }
        return users;
    }
}
