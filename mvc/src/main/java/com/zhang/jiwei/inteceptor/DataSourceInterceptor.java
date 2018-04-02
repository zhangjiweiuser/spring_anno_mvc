//package com.zhang.jiwei.inteceptor;
//
//import com.zhang.jiwei.datasource.DataSourceManager;
//import com.zhang.jiwei.datasource.DataSources;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
///**
// * Created by zhangjiwei on 2018/4/1.
// */
//@Aspect
//@Component
//@Order(0)
//public class DataSourceInterceptor {
//
//    @Pointcut("execution(* com.zhang.jiwei.slavedao.RoleDao.*(..))")
//    public void dataSourceSlave() {
//    }
//
//    @Pointcut("execution(* com.zhang.jiwei.masterdao.UserDao.*(..))")
//    public void dataSourceMaster() {
//    }
//
//
//    @Before("dataSourceSlave()")
//    public void beforeSlave(JoinPoint jp) {
////        Object[] args = jp.getArgs();
////        if (args == null) {
////            DataSourceManager.set(DataSources.SLAVE);
////            //return;
////        }
//        //System.out.println("-------------" + args[0]);
//        DataSourceManager.set(DataSources.SLAVE);
//    }
//
//    @Before("dataSourceMaster()")
//    public void beforeMaster(JoinPoint jp) {
//        DataSourceManager.set(DataSources.MASTER);
//    }
//
//}
