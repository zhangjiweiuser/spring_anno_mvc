package com.zhang.jiwei;

import com.zhang.jiwei.config.MvcConfig;
import com.zhang.jiwei.config.SpringConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author jiwei.zhang
 * @DATE 2017/12/18 0018
 */
public class StartUp {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        WebAppContext webContext = new WebAppContext();
        WebAppContext context = new WebAppContext(System.getProperty("user.dir") + "/src/main/webapp", "/");
        context.setMaxFormContentSize(1024*1024*60);
        server.setHandler(context);
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.register(SpringConfig.class);
//        context.register(MvcConfig.class);
//        webContext.addServlet(new ServletHolder(new DispatcherServlet((WebApplicationContext) context)),"/");
//        webContext.addEventListener(new ContextLoaderListener((WebApplicationContext) context));
//        server.setHandler(webContext);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                server.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        server.start();
        server.join();
    }
}
