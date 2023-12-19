package com.henry.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.henry.repository.company",
        entityManagerFactoryRef = "companyEntityManager",
        transactionManagerRef = "companyTransactionManager"
)
public class PostgreConfig {

    @Autowired
    private DataSourceProperties dsProperties;

    @Bean
    public LocalContainerEntityManagerFactoryBean companyEntityManager()
            throws NamingException {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(companyDataSource());
        em.setPackagesToScan("com.henry.model.company");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(companyHibernateProperties());

        return em;
    }

    @Bean
    public DataSource companyDataSource() throws IllegalArgumentException, NamingException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dsProperties.getPostgres().driverClassName());
        dataSource.setUrl(dsProperties.getPostgres().url());
        dataSource.setUsername(dsProperties.getPostgres().username());
        dataSource.setPassword(dsProperties.getPostgres().password());

        return dataSource;
    }

    private Properties companyHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", dsProperties.getPostgres().ddlAuto());

        return properties;
    }

    @Bean
    public PlatformTransactionManager companyTransactionManager() throws NamingException {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(companyEntityManager().getObject());
        return transactionManager;
    }
}
