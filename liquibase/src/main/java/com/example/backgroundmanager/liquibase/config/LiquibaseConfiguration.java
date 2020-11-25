package com.example.backgroundmanager.liquibase.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author as huangzd
 */
@Configuration
public class LiquibaseConfiguration {

    @Value("${custom.liquibase-contexts}")
    private String contexts;

    @Value("${custom.changeLog}")
    private String changeLog;

    @Value("${app.dataSource.url}")
    private String url;

    @Value("${app.dataSource.username}")
    private String username;

    @Value("${app.dataSource.password}")
    private String password;

    @Value("${app.dataSource.driverClassName}")
    private String driverClassName;


//    /**
//     * 用户模块Liquibase
//     */
//    @Bean
//    public SpringLiquibase userLiquibase(DataSource dataSource) {
//        SpringLiquibase liquibase = new SpringLiquibase();
//        // 用户模块Liquibase文件路径
//        liquibase.setChangeLog("classpath:liquibase/user/master.xml");
//        liquibase.setDataSource(dataSource);
//        liquibase.setShouldRun(true);
//        liquibase.setResourceLoader(new DefaultResourceLoader());
//        // 覆盖Liquibase changelog表名
//        liquibase.setDatabaseChangeLogTable("user_changelog_table");
//        liquibase.setDatabaseChangeLogLockTable("user_changelog_lock_table");
//        return liquibase;
//    }
//
//    /**
//     * 订单模块Liquibase
//     */
//    @Bean
//    public SpringLiquibase orderLiquibase() {
//        SpringLiquibase liquibase = new SpringLiquibase();
//        liquibase.setChangeLog("classpath:liquibase/order/master.xml");
//        liquibase.setDataSource(dataSource);
//        liquibase.setShouldRun(true);
//        liquibase.setResourceLoader(new DefaultResourceLoader());
//        liquibase.setDatabaseChangeLogTable("order_changelog_table");
//        liquibase.setDatabaseChangeLogLockTable("order_changelog_lock_table");
//        return liquibase;
//    }

    @Bean
    public SpringLiquibase appLiquibase() {
        SpringLiquibase liquibase = createDataSource(url, username, password, driverClassName, changeLog);
        return liquibase;
    }

    private SpringLiquibase createDataSource(String url, String username, String password, String driverClassName, String changeLog) {
        DataSource build = DataSourceBuilder.create()
                .driverClassName(driverClassName)
                .url(url)
                .username(username)
                .password(password)
                .type(com.alibaba.druid.pool.DruidDataSource.class)
                .build();
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(build);
        springLiquibase.setChangeLog(changeLog);
        springLiquibase.setContexts(contexts);
        return springLiquibase;
    }
}
