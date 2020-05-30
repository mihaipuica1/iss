package com.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.util.concurrent.TimeUnit;


@Configuration
@EnableTransactionManagement
@ComponentScan({"com.entities", "com.model"})
@EnableJpaRepositories({"com.repository"})
public class JPAConfig {

    @Bean(name = "hikariDataSource")
    public HikariDataSource hikariDataSource(){
        HikariConfig config = new HikariConfig();
        config.setMinimumIdle(3);
        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(3000);
        config.setIdleTimeout(TimeUnit.SECONDS.toMillis(10));
        config.setValidationTimeout(TimeUnit.SECONDS.toMillis(2));
        config.setDriverClassName(org.postgresql.Driver.class.getName());

<<<<<<< HEAD
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/mybaseiss");
        config.setUsername("postgres");
        config.setPassword("gloria99");
=======
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/iss2");
        config.setUsername("postgres");
        config.setPassword("admin");
>>>>>>> 639d5c2eec2460d3ae0fd5eda99992108c805ba3

        return new HikariDataSource(config);
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(hikariDataSource());
        factoryBean.setPackagesToScan("com");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}

