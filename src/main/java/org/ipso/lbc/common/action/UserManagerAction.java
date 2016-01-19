/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.action;

import com.opensymphony.xwork2.ActionSupport;
import org.ipso.lbc.common.adm.user.User;
import org.ipso.lbc.common.domain.dao.DAOUser;

import java.util.List;

/**
 * 信息：李倍存 创建于 2015-07-03 9:26。电邮 1174751315@qq.com。<br>
 * 说明：
 */
public class UserManagerAction extends ActionSupport {
    DAOUser daoUser;

    /** 添加用户账户，必须在{@link #existUser existUser}之后调用该函数。因为该函数用到exist成员。
     * @return 无
     * @throws Exception
     */
    public String addUser() throws Exception{
        warning="OK";
        if (daoUser.query(username)!=null){
            warning="用户名 "+username+" 已存在。\n";
            return SUCCESS;
        }else {
            daoUser.insertOrUpdate(new User(username,password,"user"));
            update();
        }
        return SUCCESS;
    }

    public String deleteUser()throws Exception{
        warning="OK";
        if (daoUser.query(username)!=null){
            daoUser.delete(username);
            update();
            return SUCCESS;
        }else{
            warning="用户名 "+username+" 不存在";
            return SUCCESS;
        }
    }
    public String changeUser()throws Exception{
        warning="OK";
        if (daoUser.query(username)!=null){
            daoUser.delete(username);
            daoUser.insertOrUpdate(new User(username,password,"user"));
            update();
            return SUCCESS;
        }else {
            warning="用户名 "+username+" 不存在";
            return SUCCESS;
        }

    }
    public String queryUsers()throws Exception{
        warning="OK";
        update();
        return SUCCESS;
    }

    private List<User> users;
    private void update(){
        users=daoUser.query();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * @return 无
     * @throws Exception
     */
    public String existUser()throws Exception{
        exist=(daoUser.query(username)!=null);
        return SUCCESS;
    }

    /**
     * 用户名。
     */
    private String username="";
    /**
     * 明文密码。
     */
    private String password="";
    /**
     * 指明用户账户是否已存在。
     */
    private Boolean exist=true;

    private String warning="OK";

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }
}
