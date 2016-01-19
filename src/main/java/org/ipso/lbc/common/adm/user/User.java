/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.adm.user;

import org.ipso.lbc.common.ado.IPrintable;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

/**
 * 创建：2015/1/21 9:31
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 */
public class User implements IPrintable {
    private String username;
    private String password;
    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
    public void addRole(String role){
        if (this.roles==null){
            roles=new HashSet<String>();
        }
        roles.add(role);
    }
    private Set<String> roles;

    public User() {
    }

    public User(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        addRole(role);
    }
    public User(String username, String password) {

        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }




    @Override
    public void print(PrintStream ps) {
        ps.println("用户名  " + username + ";  " + "密码  " + password + ";");
    }

    @Override
    public User clone() {
        Set<String> roles=new HashSet<String>();
        Object[] unnamed=this.roles.toArray();
        for (int i = 0; i < unnamed.length; i++) {
            roles.add((String)unnamed[i]);
        }
        User user =new User(getUsername(),getPassword(),roles);
        return user;
    }
}
