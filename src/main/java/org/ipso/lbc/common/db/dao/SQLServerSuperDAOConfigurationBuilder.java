package org.ipso.lbc.common.db.dao;

public class SQLServerSuperDAOConfigurationBuilder extends ServerBasedDatabaseSuperDAOConfigurationBuilder {
    @Override
    protected String getDefaultPort() {
        return "1433";
    }

    @Override
    protected String getDefaultJdbcDriverId() {
        return "sqlserver";
    }

    @Override
    protected String getDatabaseNamePartInUrl(String databaseName) {
        return ";DatabaseName=" + databaseName;
    }
}
