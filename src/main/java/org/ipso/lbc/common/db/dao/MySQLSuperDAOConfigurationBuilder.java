package org.ipso.lbc.common.db.dao;

public class MySQLSuperDAOConfigurationBuilder extends ServerBasedDatabaseSuperDAOConfigurationBuilder {
    @Override
    protected String getDefaultPort() {
        return "3306";
    }

    @Override
    protected String getDefaultDatabaseName() {
        return "test";
    }

    @Override
    protected String getDefaultUsername() {
        return "root";
    }

    @Override
    protected String getDefaultDriverClass() {
        return "com.mysql.jdbc.Driver";
    }

    @Override
    protected String getDefaultSqlDialect() {
        return "org.hibernate.dialect.MySQLDialect";
    }

    @Override
    protected String getDefaultJdbcDriverId() {
        return "mysql";
    }
}
