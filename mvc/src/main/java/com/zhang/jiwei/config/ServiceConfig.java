package com.zhang.jiwei.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiwei.zhang
 * @DATE 2018-03-30 下午 17:19
 */
@Configuration
@ComponentScan(basePackages = "com.zhang.jiwei.service,com.zhang.jiwei.inteceptor",
        includeFilters = {@ComponentScan.Filter(value = Repository.class),
                @ComponentScan.Filter(value = Service.class),
                @ComponentScan.Filter(value = Component.class)},
        excludeFilters = {@ComponentScan.Filter(value = Controller.class),
                @ComponentScan.Filter(value = RestController.class)})
public class ServiceConfig {

}
