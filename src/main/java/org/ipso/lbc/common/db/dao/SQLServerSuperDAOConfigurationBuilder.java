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

    @Override
    protected String getDefaultDatabaseName() {
        return "tempdb";
    }

    @Override
    protected String getDefaultUsername() {
        return "sa";
    }

    @Override
    protected String getDefaultDriverClass() {
        return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    }

    @Override
    protected String getDefaultSqlDialect() {
        return "org.hibernate.dialect.SQLServer2012Dialect";
    }
}
