package com.example.twodatabases;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

//    @Primary
    @Bean
    public JdbcTemplate mysqlJdbcTemplate(DataSource mysqlDataSource){
        return new JdbcTemplate(mysqlDataSource);
    }

  //  @Primary
    @Bean
    public DataSource mysqlDataSource(
            @Value("${spring.datasource.driver-class-name}") String mysql_driverClassName,
            @Value("${spring.datasource.url}") String mysql_url,
            @Value("${spring.datasource.username}") String mysql_username,
            @Value("${spring.datasource.password}")String mysql_password
    ){
        return DataSourceBuilder.create()
                .driverClassName(mysql_driverClassName)
                .url(mysql_url)
                .username(mysql_username)
                .password(mysql_password)
                .build();
    }

    @Bean
    public JdbcTemplate postgresJdbcTemplate(DataSource postgresDataSource){
        return new JdbcTemplate(postgresDataSource);
    }

    @Bean
    public DataSource postgresDataSource(
            @Value("${spring.datasource.postgresql.driver-class-name}") String postgres_driverClassName,
            @Value("${spring.datasource.postgresql.url}") String postgres_url,
            @Value("${spring.datasource.postgresql.username}") String postgres_username,
            @Value("${spring.datasource.postgresql.password}")String postgres_password
    ){
        return DataSourceBuilder.create()
                .driverClassName(postgres_driverClassName)
                .url(postgres_url)
                .username(postgres_username)
                .password(postgres_password)
                .build();
    }
}
