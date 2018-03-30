package com.zhang.jiwei.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author jiwei.zhang
 * @DATE 2017/12/18 0018
 */
public class CharInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(CharInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("request url : {}",request.getRequestURI());
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        return super.preHandle(request, response, handler);
    }
}
