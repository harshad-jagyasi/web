package com.example.web.configuration;

import com.google.common.base.Preconditions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.inject.Inject;
import javax.sql.DataSource;

//@Configuration
@PropertySource("classpath:application.properties")
public class JdbcConfiguration {

    @Inject
    private Environment environment;

    //@Bean
    public DataSource dbtableDataSource()
    {
        return createDataSource("dbTable");
    }

    private DataSource createDataSource(String name){
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(environment.getProperty(name + ".driver-class-name")));
        dataSource.setUrl(Preconditions.checkNotNull(environment.getProperty(name + ".url")));
        dataSource.setUsername(Preconditions.checkNotNull(environment.getProperty(name + ".username")));
        dataSource.setPassword(Preconditions.checkNotNull(environment.getProperty(name + ".password")));

        return dataSource;
    }
}
