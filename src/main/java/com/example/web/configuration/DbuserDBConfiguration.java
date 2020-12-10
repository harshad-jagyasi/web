package com.example.web.configuration;

import com.example.web.dao.DbUserDAO;
import com.example.web.dao.TransactionalDbUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

//@Configuration
@ComponentScan(basePackageClasses = {TransactionalDbUserDao.class})
@EnableTransactionManagement
public class DbuserDBConfiguration extends DefaultJPAProperties {

    public static final String Dbuser_DATASOURCE = "dbuserDatasource";
    public static final String DB_ENTITY_MANAGER_FACTORY = "entityFactory";
    public static final String DB_TRANSACTION_MANAGER = "transactionManager";

    @Autowired
    @Qualifier(Dbuser_DATASOURCE)
    private DataSource dbuserDatasource;

    @Bean(name = Dbuser_DATASOURCE)
    @Profile("!localDatasource")
    public DataSource dbUserDatasource()
    {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DbUserDAO dbUserDAO(){
        return new DbUserDAO(dbUserDatasource());
    }

    @Bean(name = DB_ENTITY_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(){
        final LocalContainerEntityManagerFactoryBean factotyBean = new LocalContainerEntityManagerFactoryBean();
        factotyBean.setDataSource(dbUserDatasource());
        factotyBean.setPackagesToScan();
        factotyBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        final Properties jpaProperties = jpaProperties();
        factotyBean.setJpaProperties(jpaProperties);
        factotyBean.setPersistenceUnitName(DB_ENTITY_MANAGER_FACTORY);
        return factotyBean;
    }

    @Bean(name = DB_TRANSACTION_MANAGER)
    public PlatformTransactionManager transactionManager(@Qualifier(DB_ENTITY_MANAGER_FACTORY) final EntityManagerFactory emf){
        final JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(emf);
        return jpaTransactionManager;
    }

}
