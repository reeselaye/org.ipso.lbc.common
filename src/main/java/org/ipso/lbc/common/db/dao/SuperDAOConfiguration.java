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

import org.hibernate.cfg.Configuration;

/**
 * Created by John Resse (2016/3/22 10:04), contact mister.resse@outlook.com.<br>
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
