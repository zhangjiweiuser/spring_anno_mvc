package com.zhang.jiwei.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.lang.annotation.Annotation;

/**
 * Created by zhangjiwei on 2018/4/1.
 */
public class MyConfigurer extends MapperScannerConfigurer {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {
        Annotation[] annotations = registry.getClass().getAnnotations();
        for(Annotation annotation : annotations){
            System.out.println(annotation.toString());
        }
        super.postProcessBeanDefinitionRegistry(registry);
    }


}
