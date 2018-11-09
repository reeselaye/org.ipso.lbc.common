package org.ipso.lbc.common.db.dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class MySQLSuperDAOConfigurationBuilderTest {

//    @Test
    public void all() {
        MySQLSuperDAOConfigurationBuilder builder = new MySQLSuperDAOConfigurationBuilder();

        builder.setHostKey("db.host");
        builder.setPortKey("db.port");
        builder.setDatabaseNameKey("db.name");
        builder.setUsernameKey("db.username");
        builder.setPasswordKey("db.password");

        SuperDAOConfiguration conf = builder.createNew("hibernate.cfg.main");
    }
}