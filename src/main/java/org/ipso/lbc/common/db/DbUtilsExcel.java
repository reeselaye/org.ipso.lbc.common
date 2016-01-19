/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package org.ipso.lbc.common.db;

import org.apache.commons.dbutils.DbUtils;
import org.ipso.lbc.common.exception.DatabaseAccessException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by LBC on 2015/1/22.
 */
public class DbUtilsExcel implements IDbUtils {
    public DbUtilsExcel() {
    }

    public static final String DB_URL = "jdbc:Excel:/D:\\系统文档\\Documents\\IdeaProjects\\TestStruts2\\src\\main.java.db\\basic\\20141014.xlsx";
    public static final String DRIVER = "com.hxtt.sql.excel.ExcelDriver";
    public static final String USERNAME = "SCOTT";
    public static final String PASSWORD = "123456";

    public Connection getConnection(String url, String drv, String username, String password) {
        DbUtils.loadDriver(drv);
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new DatabaseAccessException(e);
        }
    }

    public Connection getDefaultConnection() throws Exception {
        try {
            Class.forName(DRIVER);
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        } catch (Exception e) {
            System.out.println("Error");
        }

        return getConnection(DB_URL, DRIVER, "", "");
    }
}
