package org.ipso.lbc.common.db.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by lbc on 2016/3/22.
 */
public class SuperDAOConfiguration {
    private Configuration configuration;
    private SuperDAOConfiguration(Configuration configuration){
        this.configuration = configuration;
    }
    public SuperDAOConfiguration(String configurationFileName){
        this(new Configuration().configure(configurationFileName));
    }

    public SuperDAOConfiguration set(String key, String value){
        configuration.setProperty(key, value);
        return this;
    }
    public SuperDAOConfiguration setUrl(String url){
        return set("hibernate.connection.url", url);
    }
    public SuperDAOConfiguration setUserName(String userName){
        return set("hibernate.connection.username", userName);
    }
    public SuperDAOConfiguration setPassword(String password){
        return set("hibernate.connection.password", password);
    }
    public SuperDAOConfiguration setDriverClass(String driverClass){
        return set("hibernate.connection.driver_class", driverClass);
    }
    public SuperDAOConfiguration setDialect(String dialectClass){
        return set("hibernate.dialect", dialectClass);
    }

    public Configuration getConfiguration(){
        return configuration;
    }


}
