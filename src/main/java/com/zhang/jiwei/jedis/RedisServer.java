package com.zhang.jiwei.jedis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.zhang.jiwei.config.DaoConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @author jiwei.zhang
 * @DATE 2018/1/3 0003
 */
public class RedisServer {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DaoConfig.class);
        context.refresh();
        JedisConnectionFactory factory = context.getBean(JedisConnectionFactory.class);
        RedisConnection connection = factory.getConnection();
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(()->{
            connection.subscribe(new RedisMessageListener(),"message".getBytes());
        });
        System.out.println("server start");
    }
}
