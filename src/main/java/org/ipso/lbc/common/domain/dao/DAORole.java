/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.domain.dao;

import org.ipso.lbc.common.db.dao.AbstractDAO;
import org.ipso.lbc.common.db.dao.SuperDAO;
import org.ipso.lbc.common.adm.user.Role;

/**
 * 李倍存 创建于 2015-05-09 20:34。电邮 1174751315@qq.com。
 */
public class DAORole extends AbstractDAO {
    public DAORole(SuperDAO superDAO) {
        this.superDAO=superDAO;
    }

    public void insert(Role role){
        superDAO.insert(role);
    }
    public void insertOrUpdate(Role role){
        superDAO.insertOrUpdate(role);
    }

    public void delete(String name){
        superDAO.delete(Role.class,name);
    }
}
