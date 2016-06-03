/*
 * Copyright the original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ipso.lbc.common.db.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.ipso.lbc.common.frameworks.logging.LoggingFacade;

/**
 * Created by John Resse (2016/3/22 10:04), contact mister.resse@outlook.com.<br>
 */
public class SessionFactoryConfiguration {
    private Configuration configuration;
    private SessionFactoryConfiguration(Configuration configuration){
        this.configuration = configuration;
    }
    public SessionFactoryConfiguration(String configurationFileName){
        this(new Configuration().configure(configurationFileName));
    }

    public SessionFactoryConfiguration set(String key, String value){
        configuration.setProperty(key, value);
        return this;
    }
    public SessionFactoryConfiguration setUrl(String url){
        return set("hibernate.connection.url", url);
    }
    public SessionFactoryConfiguration setUserName(String userName){
        return set("hibernate.connection.username", userName);
    }
    public SessionFactoryConfiguration setPassword(String password){
        return set("hibernate.connection.password", password);
    }
    public SessionFactoryConfiguration setDriverClass(String driverClass){
        return set("hibernate.connection.driver_class", driverClass);
    }
    public SessionFactoryConfiguration setDialect(String dialectClass){
        return set("hibernate.dialect", dialectClass);
    }
    public SessionFactory buildSessionFactory(){
        SessionFactory sessionFactory = null;
        try {
            LoggingFacade.debug("Building the Hibernate SessionFactory...");
            sessionFactory = getConfiguration().buildSessionFactory();
            LoggingFacade.info("Successfully to build the Hibernate SessionFactory ({everything of database works fine}). ([" + sessionFactory.toString() + "])");
        } catch (HibernateException e){
            LoggingFacade.fatal("The application is terminated because we {cannot build the Hibernate SessionFactory}, perhaps due to {failure of database accessing.}");
            System.exit(-1);
        }
        return sessionFactory;
    }

    private Configuration getConfiguration(){
        return configuration;
    }




}
