package com.zhang.jiwei.config;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.zhang.jiwei.datasource.DataSource.Type;
import com.zhang.jiwei.datasource.DataSources;
import com.zhang.jiwei.datasource.DynamicRoutingDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * @author jiwei.zhang
 * @DATE 2018-03-30 下午 14:58
 */
@Configuration
@PropertySource(value = {"classpath:jdbc.properties"}, ignoreResourceNotFound = true)
public class MybatisConfig implements EnvironmentAware {

    private Environment environment;

    @Bean
    public DruidXADataSource dataSource() {
        DruidXADataSource dataSource = new DruidXADataSource();
//        DruidDataSource dataSource = new DruidDataSource();
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
    public DruidXADataSource slaveDataSource() {
        DruidXADataSource dataSource = new DruidXADataSource();
//        DruidDataSource dataSource = new DruidDataSource();
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
    public AtomikosDataSourceBean atomikosMasterDataSourceBean(DruidXADataSource dataSource) {
        AtomikosDataSourceBean dataSourceBean = new AtomikosDataSourceBean();
//        dataSourceBean.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        dataSourceBean.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
        dataSourceBean.setUniqueResourceName("masterjta");
//        dataSourceBean.setXaDataSource(dataSource);
        Properties properties = new Properties();
        properties.setProperty("url",environment.getProperty("master.url"));
        properties.setProperty("user",environment.getProperty("master.jdbc.username"));
        properties.setProperty("password",environment.getProperty("master.password"));
        dataSourceBean.setXaProperties(properties);
        dataSourceBean.setMaintenanceInterval(28000);
        return dataSourceBean;
    }

    @Bean
    public AtomikosDataSourceBean atomikosSlaveDataSourceBean(DruidXADataSource slaveDataSource) {
        AtomikosDataSourceBean dataSourceBean = new AtomikosDataSourceBean();
//        dataSourceBean.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        dataSourceBean.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
        dataSourceBean.setUniqueResourceName("slavejta");
//        dataSourceBean.setXaDataSource(slaveDataSource);
        Properties properties = new Properties();
        properties.setProperty("url",environment.getProperty("slave.url"));
        properties.setProperty("user",environment.getProperty("slave.jdbc.username"));
        properties.setProperty("password",environment.getProperty("slave.password"));
        dataSourceBean.setXaProperties(properties);
        dataSourceBean.setMaintenanceInterval(28000);
        return dataSourceBean;
    }

//    @Bean
//    public DataSource routingDataSource(AtomikosDataSourceBean atomikosMasterDataSourceBean, AtomikosDataSourceBean atomikosSlaveDataSourceBean) {
//        DynamicRoutingDataSource routingDataSource = new DynamicRoutingDataSource();
//        routingDataSource.setDefaultTargetDataSource(atomikosMasterDataSourceBean);
//        Map dataSourcesMap = new ConcurrentHashMap();
//        dataSourcesMap.put(DataSources.MASTER, atomikosMasterDataSourceBean);
//        dataSourcesMap.put(DataSources.SLAVE, atomikosSlaveDataSourceBean);
//        routingDataSource.setTargetDataSources(dataSourcesMap);
//        return routingDataSource;
//    }

    @Bean
    public SqlSessionFactoryBean masterSqlSessionFactoryBean(AtomikosDataSourceBean atomikosMasterDataSourceBean) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(atomikosMasterDataSourceBean);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:usermapper/*Mapper.xml"));
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        factoryBean.setConfiguration(configuration);
        factoryBean.setTypeAliasesPackage("com.zhang.jiwei.entity");
        return factoryBean;
    }

    @Bean
    public SqlSessionFactoryBean slaveSqlSessionFactoryBean(AtomikosDataSourceBean atomikosSlaveDataSourceBean) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(atomikosSlaveDataSourceBean);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:rolemapper/*Mapper.xml"));
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        factoryBean.setConfiguration(configuration);
        factoryBean.setTypeAliasesPackage("com.zhang.jiwei.entity");
        return factoryBean;
    }
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DruidXADataSource slaveDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(slaveDataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*Mapper.xml"));
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        factoryBean.setConfiguration(configuration);
        factoryBean.setTypeAliasesPackage("com.zhang.jiwei.entity");
        return factoryBean;
    }

    @Bean
    public MapperScannerConfigurer masterMapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.zhang.jiwei.masterdao");
        configurer.setSqlSessionFactoryBeanName("masterSqlSessionFactoryBean");
        configurer.setAddToConfig(true);
        return configurer;
    }

    @Bean
    public MapperScannerConfigurer slaveMapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.zhang.jiwei.slavedao");
        configurer.setSqlSessionFactoryBeanName("slaveSqlSessionFactoryBean");
        configurer.setAddToConfig(true);
        return configurer;
    }
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.zhang.jiwei.dao");
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        configurer.setAddToConfig(true);
        return configurer;
    }

//    @Bean
//    public DataSourceTransactionManager dataSourceTransactionManager(DataSource routingDataSource) {
//        DataSourceTransactionManager manager = new DataSourceTransactionManager();
//        manager.setDataSource(routingDataSource);
//        return manager;
//    }

    @Bean
    public UserTransactionManager userTransactionManager() throws SystemException {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(true);
        return userTransactionManager;
    }

    @Bean
    public UserTransactionImp userTransactionImp() throws SystemException, NotSupportedException {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(90000);
        return userTransactionImp;
    }

    @Bean
    public JtaTransactionManager transactionManager(UserTransactionManager userTransactionManager, UserTransactionImp userTransactionImp) {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setTransactionManager(userTransactionManager);
        jtaTransactionManager.setUserTransaction(userTransactionImp);
        jtaTransactionManager.setAllowCustomIsolationLevels(true);
        return jtaTransactionManager;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
