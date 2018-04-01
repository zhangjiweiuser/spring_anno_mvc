package com.zhang.jiwei.inteceptor;

import com.zhang.jiwei.config.DataSourceType;
import com.zhang.jiwei.datasource.DataSourceManager;
import com.zhang.jiwei.datasource.DataSources;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * Created by zhangjiwei on 2018/4/1.
 */
@Aspect
@Component
@Order(0)
public class DataSourceInterceptor {

    @Pointcut("execution(* com.zhang.jiwei.dao.**.*(..))")
    public void dataSourceSlave(){
    }

    @Before("dataSourceSlave()")
    public void before(JoinPoint joinPoint){

        Annotation[] annotations = joinPoint.getSignature().getDeclaringType().getAnnotations();
        for(Annotation annotation : annotations) {
            System.out.println(annotation.annotationType().getName());
            System.out.println(annotation.toString());
            if(annotation instanceof DataSourceType){
                if(((DataSourceType) annotation).value().equals(DataSources.SLAVE)){
                    DataSourceManager.reset();
                    DataSourceManager.set(DataSources.SLAVE);
                    break;
                }else if(((DataSourceType) annotation).value().equals(DataSources.MASTER)){
                    DataSourceManager.reset();
                    DataSourceManager.set(DataSources.MASTER);
                    break;
                }
            }

        }
    }
}
