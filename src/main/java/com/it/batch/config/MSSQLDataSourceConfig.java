package com.it.batch.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Haytham DAHRI
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mssqlEntityManagerFactory",
        transactionManagerRef = "mssqlTransactionManager",
        basePackages = {"com.it.batch.dao.mssql"})
public class MSSQLDataSourceConfig {

    @Value("${mssql.jpa.hibernate.dialect}")
    private String hibernateDialect;

    @Bean(name = "mssqlDataSource")
    @ConfigurationProperties(prefix = "mssql.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mssqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean msEntityManagerFactory(@Qualifier("entityManagerFactoryBuilder") EntityManagerFactoryBuilder entityManagerFactoryBuilder,
                                                                         @Qualifier("mssqlDataSource") DataSource dataSource) {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.dialect", hibernateDialect);
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .properties(props)
                .packages("com.it.batch.dao.mssql")
                .persistenceUnit("mssql_unit")
                .build();
    }

    @Bean(name = "mssqlTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("mssqlEntityManagerFactory") EntityManagerFactory msEntityManagerFactory) {
        return new JpaTransactionManager(msEntityManagerFactory);
    }
}
