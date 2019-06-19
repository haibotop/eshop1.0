package com.gsj.www.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.sql.SQLException;

@Configuration
public class DruidDataSourceConfig {
    @Autowired
    private Environment env;
    @Bean(name = "dataSource",initMethod = "init",destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.druid.dataSource")
    public DruidDataSource readDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        try {
            dataSource.setFilters("config");
//            Properties properties = new Properties();
//            properties.put("config.decrypt",env.getProperty("druid.config.decrypt"));
//            properties.put("config.decrypt.key",env.getProperty("druid.config.decrypt.key"));
//            dataSource.setConnectProperties(properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}