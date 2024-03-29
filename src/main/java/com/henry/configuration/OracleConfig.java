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
        basePackages = "com.henry.repository.brand",
        entityManagerFactoryRef = "brandEntityManager",
        transactionManagerRef = "brandTransactionManager"
)
public class OracleConfig {

    @Autowired
    private DataSourceProperties dsProperties;

    @Bean
    public LocalContainerEntityManagerFactoryBean brandEntityManager()
            throws NamingException {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(brandDataSource());
        em.setPackagesToScan("com.henry.model.brand");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(brandHibernateProperties());

        return em;
    }

    @Bean
    public DataSource brandDataSource() throws IllegalArgumentException, NamingException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dsProperties.getOracle().driverClassName());
        dataSource.setUrl(dsProperties.getOracle().url());
        dataSource.setUsername(dsProperties.getOracle().username());
        dataSource.setPassword(dsProperties.getOracle().password());

        return dataSource;
    }

    private Properties brandHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", dsProperties.getOracle().ddlAuto());

        return properties;
    }


    @Bean
    public PlatformTransactionManager brandTransactionManager() throws NamingException {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(brandEntityManager().getObject());
        return transactionManager;
    }
}
