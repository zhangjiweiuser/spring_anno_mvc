package com.zhang.jiwei.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author jiwei.zhang
 * @DATE 2017/12/18 0018
 */
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@Import({ServiceConfig.class, MybatisConfig.class})
public class SpringConfig {

}
