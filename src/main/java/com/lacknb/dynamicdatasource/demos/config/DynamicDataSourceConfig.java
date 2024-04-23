package com.lacknb.dynamicdatasource.demos.config;

import com.alibaba.druid.pool.DruidDataSource;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @author gitsilence
 * @date 2024-04-23
 */
@Configuration
public class DynamicDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return new DruidDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return new DruidDataSource();
    }

    @Bean
    public DynamicDataSource dynamicDataSource() {
        Map<Object, Object> dataSourceMap = new HashMap<Object, Object>();
        dataSourceMap.put("primary", primaryDataSource());
        dataSourceMap.put("secondary", secondaryDataSource());

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.setDefaultTargetDataSource(primaryDataSource());
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*Mapper.xml");

        sqlSessionFactoryBean.setMapperLocations(resources);
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        return sqlSessionFactoryBean.getObject();
    }

}
