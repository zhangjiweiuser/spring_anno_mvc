package com.zhang.jiwei.config;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.zhang.jiwei.converter.JsonHttpMessageConverter;
import com.zhang.jiwei.inteceptor.CharInterceptor;
import com.zhang.jiwei.inteceptor.DataSourceInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static com.alibaba.fastjson.util.IOUtils.UTF8;

/**
 * @author jiwei.zhang
 * @DATE 2017/12/18 0018
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.zhang.jiwei.controller",
        includeFilters = {@ComponentScan.Filter(value = Controller.class),
                @ComponentScan.Filter(value = RestController.class)})
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(new JsonHttpMessageConverter());
        super.configureMessageConverters(converters);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new CharInterceptor()).addPathPatterns("/**");
//        super.addInterceptors(registry);
//    }



}
