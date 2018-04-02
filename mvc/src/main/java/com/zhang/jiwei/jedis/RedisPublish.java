package com.zhang.jiwei.jedis;

import com.zhang.jiwei.config.DaoConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author jiwei.zhang
 * @DATE 2018/1/3 0003
 */
public class RedisPublish {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DaoConfig.class);
        context.refresh();
        RedisTemplate template = context.getBean(RedisTemplate.class);
        template.convertAndSend("message","hello 3");
    }
}
