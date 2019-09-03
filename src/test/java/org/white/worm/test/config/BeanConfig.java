package org.white.worm.test.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.white.worm.jdbc.WJdbcTemplate;

import javax.sql.DataSource;

/**
 * <p></p >
 *
 * @author baixiong
 * @version $Id: BeanConfig.java, v 0.1 2019年09月03日 16:44:00 baixiong Exp$
 */
@Configuration
@PropertySource("classpath:applications.properties")
public class BeanConfig {

    @Bean
    @ConfigurationProperties()
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "dataSource")
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        return DataSourceBuilder.create(dataSourceProperties.getClass().getClassLoader())
                .type(HikariDataSource.class)
                .driverClassName(dataSourceProperties.getDriverClassName())
                .url(dataSourceProperties.getJdbcUrl())
                .username(dataSourceProperties.getUser())
                .password(dataSourceProperties.getPassword())
                .build();
    }

    @Bean(name = "wJdbcTemplate")
    public JdbcTemplate jdbcTemplate(
            @Qualifier("dataSource") DataSource dataSource) {
        return new WJdbcTemplate(dataSource);
    }
}
