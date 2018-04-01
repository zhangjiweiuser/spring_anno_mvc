package com.zhang.jiwei.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.zhang.jiwei.datasource.DataSources;
import com.zhang.jiwei.datasource.ThreadLocalRountingDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.annotation.Before;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
        dataSource.setUrl(environment.getProperty("master.url"));
        dataSource.setUsername(environment.getProperty("master.jdbc.username"));
        dataSource.setPassword(environment.getProperty("master.password"));
        dataSource.setMaxActive(5);
        dataSource.setMinIdle(3);
        dataSource.setInitialSize(3);
        dataSource.setMaxWait(5000L);
        dataSource.setTestWhileIdle(true);
        dataSource.setValidationQuery("SELECT 1");
        return dataSource;
    }

    @Bean
    public DataSource slaveDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(environment.getProperty("driver_class_name"));
        dataSource.setUrl(environment.getProperty("slave.url"));
        dataSource.setUsername(environment.getProperty("slave.jdbc.username"));
        dataSource.setPassword(environment.getProperty("slave.password"));
        dataSource.setMaxActive(5);
        dataSource.setMinIdle(3);
        dataSource.setInitialSize(3);
        dataSource.setMaxWait(5000L);
        dataSource.setTestWhileIdle(true);
        dataSource.setValidationQuery("SELECT 1");
        return dataSource;
    }

    @Bean
    public ThreadLocalRountingDataSource rountingDataSource(DataSource dataSource,DataSource slaveDataSource){
        ThreadLocalRountingDataSource rountingDataSource = new ThreadLocalRountingDataSource();
        rountingDataSource.setDefaultTargetDataSource(dataSource);
        Map dataSourcesMap = new ConcurrentHashMap();
        dataSourcesMap.put(DataSources.MASTER,dataSource);
        dataSourcesMap.put(DataSources.SLAVE,slaveDataSource);
        rountingDataSource.setTargetDataSources(dataSourcesMap);
        rountingDataSource.afterPropertiesSet();
        return rountingDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(ThreadLocalRountingDataSource rountingDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(rountingDataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*Mapper.xml"));
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        factoryBean.setConfiguration(configuration);
        factoryBean.setTypeAliasesPackage("com.zhang.jiwei.entity");
        return factoryBean.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.zhang.jiwei.dao");
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        configurer.setAddToConfig(true);
        return configurer;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(ThreadLocalRountingDataSource rountingDataSource){
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(rountingDataSource);
        manager.afterPropertiesSet();
        return manager;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
