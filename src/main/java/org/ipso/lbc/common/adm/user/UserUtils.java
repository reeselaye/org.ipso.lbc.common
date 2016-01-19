/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.adm.user;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.ipso.lbc.common.domain.dao.DAOUser;
import org.ipso.lbc.common.exception.DatabaseAccessException;

/**
 * 李倍存 创建于 2015-02-24 16:08。电邮 1174751315@qq.com。
 */
public class UserUtils {
    /** 将一个用户账户加密。
     * @param user 源，未加密用户账户。
     * @return 加密的用户账户。
     */
    public static User encript(User user){
        User clone=user.clone();
        clone.setPassword(encript(user.getPassword()));
        return clone;
    }
    public static String encript(String password){
        return new Md5Hash(password).toHex();
    }

    public static Boolean exist(String username) {
        try {
            User user = daoUser.query(username);
            return user != null;
        } catch (Exception e) {
            throw new DatabaseAccessException(e);

        }
    }

    public static Boolean validate(String username, String password) {
        try {

            User user = daoUser.query(username);
            return user != null && user.getPassword().equals(encript(password));
        } catch (Exception e) {
            throw new DatabaseAccessException(e);
        }
    }


    public static DAOUser daoUser;

    public static void add(User user) throws DatabaseAccessException {
        if (exist(user.getUsername())){
            throw new DatabaseAccessException("用户名 " + user.getUsername() + " 已被使用。");
        }
        daoUser.insert(user);
    }

    public static void add(String username, String password) throws DatabaseAccessException {
        if (exist(username)){
            throw new DatabaseAccessException("用户名 " + username + " 已被使用。");
        }
        User user = new User(username, password);
        daoUser.insert(user);
    }

    public static void delete(String username) throws DatabaseAccessException {
        if (!exist(username)){
            throw new DatabaseAccessException("用户名 " + username + " 不存在");
        }
        daoUser.delete(username);
    }

    public static void update(String username, String password) throws DatabaseAccessException {
        if (!exist(username)){
            throw new DatabaseAccessException("用户名 " + username + " 不存在");
        }
        User user = new User(username, password);
        daoUser.insertOrUpdate(user);
    }

    public static void update(User user) throws DatabaseAccessException {
        update(user.getUsername(), user.getPassword());
    }

}
