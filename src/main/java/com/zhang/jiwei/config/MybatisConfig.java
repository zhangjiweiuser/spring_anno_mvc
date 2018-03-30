package com.zhang.jiwei.config;

import java.io.IOException;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @author jiwei.zhang
 * @DATE 2018-03-30 下午 14:58
 */
@Configuration
@ComponentScan({"com.zhang.jiwei.dao"})
@PropertySource(value = {"classpath:jdbc.properties"}, ignoreResourceNotFound = true)
public class MybatisConfig implements EnvironmentAware {

    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(environment.getProperty("driver_class_name"));
        dataSource.setUrl(environment.getProperty("url"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        dataSource.setPassword(environment.getProperty("password"));
        dataSource.setMaxActive(5);
        dataSource.setMinIdle(3);
        dataSource.setInitialSize(3);
        dataSource.setMaxWait(5000L);
        dataSource.setTestWhileIdle(true);
        dataSource.setValidationQuery("SELECT 1");
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*Mapper.xml"));
//        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//        configuration.setMapUnderscoreToCamelCase(true);
//        factoryBean.setConfigLocation(configuration);
        factoryBean.setTypeAliasesPackage("com.zhang.jiwei.entity");
        return factoryBean.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.zhang.jiwei.dao");
//        configurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
//        configurer.setAddToConfig(true);
        return configurer;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
