package org.ipso.lbc.common.db.dao;

public class MySQLSuperDAOConfigurationBuilder extends ServerBasedDatabaseSuperDAOConfigurationBuilder {
    @Override
    protected String getDefaultPort() {
        return "3306";
    }

    @Override
    protected String getDefaultJdbcDriverId() {
        return "mysql";
    }
}
