/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.adm.user;

import java.util.Set;

/**
 * 李倍存 创建于 2015-05-09 19:58。电邮 1174751315@qq.com。
 */
public class Role {
    public Role() {
    }

    public Role(String name, Set<String> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public Role(String name) {

        this.name = name;
    }

    private String name;
    private Set<String> permissions;
private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}
