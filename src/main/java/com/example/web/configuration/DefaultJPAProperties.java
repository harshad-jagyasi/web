package com.example.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Properties;

@Configuration
public class DefaultJPAProperties {

    @Autowired
    protected Environment env;

    protected final Properties jpaProperties(){
        final Properties hibernateProperties = new Properties();

        final String hbm2ddl = env.getProperty("hibernate.hbm2ddl.auto");
        final String dialect = env.getProperty("hibernate.dialect");
        final String showSql = env.getProperty("hibernate.show_sql");
        final String formatSql = env.getProperty("hibernate.format_sql");
        final String newMappings = env.getProperty("hibernate.id.new_generator_mappings");

        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", hbm2ddl !=null ? hbm2ddl : "none");
        hibernateProperties.setProperty("hibernate.dialect", dialect !=null ? dialect : "org.hibernate.dialect.MySQL57Dialect");
        hibernateProperties.setProperty("hibernate.show_sql", showSql !=null ? showSql : "false");
        hibernateProperties.setProperty("hibernate.format_sql", formatSql !=null ? formatSql : "false");
        hibernateProperties.setProperty("hibernate.id.new_generator_mappings", newMappings !=null ? newMappings : "false");

        return hibernateProperties;
    }
}
