/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package org.ipso.lbc.common.db;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LBC on 2015/1/22.
 */
public class DbUtilsFactory {
    private Map<eDbType, IDbUtils> pool;
    private static DbUtilsFactory instance = null;

    private DbUtilsFactory() {
        this.pool = new HashMap<eDbType, IDbUtils>();
        pool.put(eDbType.EXCEL, new DbUtilsExcel());
        pool.put(eDbType.ORACLE, new DbUtilsOracle());
        pool.put(eDbType.ACCESS, new DbUtilsAccess());
    }

    public static DbUtilsFactory getInstance() {
        if (instance == null) {
            instance = new DbUtilsFactory();
        }
        return instance;

    }

    public IDbUtils getDefaultDbUtils(eDbType type) {
        return pool.get(type);
    }
}
