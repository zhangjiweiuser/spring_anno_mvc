package com.zhang.jiwei.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiwei.zhang
 * @DATE 2017/12/18 0018
 */
@Configuration
@Import({MybatisConfig.class,ServiceConfig.class})
public class SpringConfig {

}
