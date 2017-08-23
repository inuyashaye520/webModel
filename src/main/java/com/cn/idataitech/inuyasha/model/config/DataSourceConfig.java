package com.cn.idataitech.inuyasha.model.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(DatabaseProperties.class)
public class DataSourceConfig {

    @Autowired
    private DatabaseProperties databaseProperties;

    public DataSourceConfig() {
    }

    @Bean("defaultDataSoure")
    public DataSource defaultDataSoure() {
        String url = "jdbc:mysql://" + this.databaseProperties.getHost() + ":" + this.databaseProperties.getPort() + "/" + this.databaseProperties.getName();
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(databaseProperties.getUser());
        dataSource.setPassword(databaseProperties.getPassword());
        dataSource.setMaxActive(20);
        dataSource.setTestOnBorrow(true);
        dataSource.setValidationQuery("SELECT 1");
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource);
        return tm;
    }

    @Bean
    public DefaultTransactionDefinition transactionDefinition() {
        return new DefaultTransactionDefinition();
    }

    static {
        System.setProperty("mybatis.config-location", "classpath:mybatis.xml");
        System.setProperty("mybatis.mapper-locations", "classpath:mybatis/**/*.xml");
    }


}
