package org.ipso.lbc.common.db.dao;

import org.ipso.lbc.common.config.Configuration;
import org.ipso.lbc.common.exception.ConfigurationException;
import org.ipso.lbc.common.utils.ResourcePathHelper;

public abstract class ServerBasedDatabaseSuperDAOConfigurationBuilder {
    private String jdbcDriverId;
    private String host, port, databaseName, username, password;

    private String hostKey;
    private String portKey;
    private String databaseNameKey;
    private String usernameKey;
    private String passwordKey;

    public ServerBasedDatabaseSuperDAOConfigurationBuilder() {
        setHostKey("database.host");
        setPortKey("database.port");
        setDatabaseNameKey("database.name");
        setUsernameKey("database.username");
        setPasswordKey("database.password");
    }

    public ServerBasedDatabaseSuperDAOConfigurationBuilder setJdbcDriverId(String jdbcDriverId) {
        this.jdbcDriverId = jdbcDriverId;
        return this;
    }

    public ServerBasedDatabaseSuperDAOConfigurationBuilder setHostKey(String hostKey) {
        this.hostKey = hostKey;
        return this;
    }

    public ServerBasedDatabaseSuperDAOConfigurationBuilder setPortKey(String portKey) {
        this.portKey = portKey;
        return this;
    }

    public ServerBasedDatabaseSuperDAOConfigurationBuilder setDatabaseNameKey(String databaseNameKey) {
        this.databaseNameKey = databaseNameKey;
        return this;
    }

    public ServerBasedDatabaseSuperDAOConfigurationBuilder setUsernameKey(String usernameKey) {
        this.usernameKey = usernameKey;
        return this;
    }

    public ServerBasedDatabaseSuperDAOConfigurationBuilder setPasswordKey(String passwordKey) {
        this.passwordKey = passwordKey;
        return this;
    }

    public String getHostKey() {
        return hostKey;
    }

    public String getPortKey() {
        return portKey;
    }

    public String getDatabaseNameKey() {
        return databaseNameKey;
    }

    public String getUsernameKey() {
        return usernameKey;
    }

    public String getPasswordKey() {
        return passwordKey;
    }

    public SuperDAOConfiguration createNew(String confFileName) {
        return createNew(confFileName, false);
    }

    public SuperDAOConfiguration createNew(String confFileName, boolean relative) {
        this.update();

        SuperDAOConfiguration superDAOConfiguration = new SuperDAOConfiguration(relative? ResourcePathHelper.getAbsolutePath(confFileName): confFileName);

        superDAOConfiguration.setUserName(username);
        superDAOConfiguration.setPassword(password);
        superDAOConfiguration.setUrl("jdbc:" + jdbcDriverId + "://" + host + ":" + port + getDatabaseNamePartInUrl(databaseName));

        return superDAOConfiguration;
    }

    protected String getDefaultPort(){
        return "";
    }

    protected String getDefaultJdbcDriverId() {
        return "";
    }

    protected String getDefaultHost() {
        return "localhost";
    }

    protected String getDefaultDatabaseName() {
        throw new ConfigurationException("database.name must be explicitly provided.");
    }

    protected String getDatabaseNamePartInUrl(String databaseName) {
        return "/" + databaseName;
    }

    protected String getDefaultUsername() {
        return "";
    }

    protected String getDefaultPassword() {
        return "123456";
    }

    private void update() {
        Configuration conf = Configuration.INSTANCE;
        if (jdbcDriverId == null) {
            jdbcDriverId = getDefaultJdbcDriverId();
        }
        host = conf.getConfiguration(getHostKey());
        if (host == null) {
            host = getDefaultHost();
        }
        port = conf.getConfiguration(getPortKey());
        if (port == null) {
            port = getDefaultPort();
        }
        databaseName = conf.getConfiguration(getDatabaseNameKey());
        if (databaseName == null) {
            databaseName = getDefaultDatabaseName();
        }
        username = conf.getConfiguration(getUsernameKey());
        if (username == null) {
            username = getDefaultUsername();
        }
        password = conf.getConfiguration(getPasswordKey());
        if (password == null) {
            password = getDefaultPassword();
        }
    }
}
