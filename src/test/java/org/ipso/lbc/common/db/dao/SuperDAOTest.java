package org.ipso.lbc.common.db.dao;

import org.ipso.lbc.common.config.Configuration;
import org.junit.Test;

import static org.junit.Assert.*;

public class SuperDAOTest {

    @Test
    public void testCreate() throws Exception {
        Configuration configuration = Configuration.INSTANCE;
        SuperDAO superDAO = SuperDAO.create(
                new SuperDAOConfiguration("hibernate.cfg.main.xml")
                    .setDialect("org.hibernate.dialect.Oracle10gDialect")
                    .setDriverClass("oracle.jdbc.OracleDriver")
                    .setUrl(        "jdbc:oracle:thin:@//"
                            + configuration.getConfigurationEnsureReturn("app.database.oracle.host")
                            + ":" + configuration.getConfigurationEnsureReturn("app.database.oracle.port")
                            + "/" + configuration.getConfigurationEnsureReturn("app.database.oracle.serviceName"))
                    .setUserName(configuration.getConfigurationEnsureReturn("app.database.username"))
                    .setPassword(configuration.getConfigurationEnsureReturn("app.database.password"))
        );
    }
}