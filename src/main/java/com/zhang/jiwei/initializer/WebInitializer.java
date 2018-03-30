//package com.zhang.jiwei.initializer;
//
//import javax.servlet.Filter;
//
//import com.zhang.jiwei.config.MvcConfig;
//import com.zhang.jiwei.config.SpringConfig;
//import org.springframework.web.filter.CharacterEncodingFilter;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
///**
// * @author jiwei.zhang
// * @DATE 2017/12/18 0018
// */
//public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class<?>[]{SpringConfig.class};
//    }
//
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class<?>[]{MvcConfig.class};
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
//
//    @Override
//    protected Filter[] getServletFilters() {
//        CharacterEncodingFilter filter = new CharacterEncodingFilter();
//        filter.setEncoding("UTF-8");
//        filter.setForceEncoding(true);
//        return new Filter[]{filter};
//    }
//}
