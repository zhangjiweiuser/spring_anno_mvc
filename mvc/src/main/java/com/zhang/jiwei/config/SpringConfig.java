package com.zhang.jiwei.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author jiwei.zhang
 * @DATE 2017/12/18 0018
 */
@Configuration
@Import({ServiceConfig.class, MybatisConfig.class})
public class SpringConfig {

}
