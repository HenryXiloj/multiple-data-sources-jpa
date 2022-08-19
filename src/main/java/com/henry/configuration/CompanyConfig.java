package com.henry.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
@PropertySource(value = {"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = "com.henry.repository.company",
        entityManagerFactoryRef = "companyEntityManager",
        transactionManagerRef = "companyTransactionManager"
)
public class CompanyConfig {

    @Autowired
    private Environment environment;

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
        dataSource.setDriverClassName(environment.getProperty("app.datasource.company.driverClassName"));
        dataSource.setUrl(environment.getProperty("app.datasource.company.url"));
        dataSource.setUsername(environment.getProperty("app.datasource.company.username"));
        dataSource.setPassword(environment.getProperty("app.datasource.company.password"));

        return dataSource;
    }

    private Properties companyHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("app.datasource.company.dialect"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("app.datasource.company.ddlAuto"));

        return properties;
    }

    @Bean
    public PlatformTransactionManager companyTransactionManager() throws NamingException {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(companyEntityManager().getObject());
        return transactionManager;
    }
}
