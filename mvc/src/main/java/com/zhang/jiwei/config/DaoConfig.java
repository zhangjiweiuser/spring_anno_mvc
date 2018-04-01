package com.zhang.jiwei.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author jiwei.zhang
 * @DATE 2018/1/3 0003
 */
//@Configuration
//@PropertySource(value = {"classpath:redis.properties"}, ignoreResourceNotFound = true)
public class DaoConfig {

//    private Environment environment;
//
//    @Bean
//    public JedisPoolConfig createJedisPoolConfig(){
//        JedisPoolConfig pool = new JedisPoolConfig();
//        pool.setMaxTotal(Integer.valueOf(environment.getProperty("redis.maxTotal")));
//        pool.setMinIdle(Integer.valueOf(environment.getProperty("redis.minIdle")));
//        pool.setMaxIdle(Integer.valueOf(environment.getProperty("redis.maxIdle")));
//        pool.setTestOnBorrow(true);
//        return pool;
//    }
//
//    @Bean
//    public JedisConnectionFactory createJedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
//        JedisConnectionFactory factory = new JedisConnectionFactory();
//        factory.setHostName(environment.getProperty("redis.host"));
//        factory.setPort(Integer.valueOf(environment.getProperty("redis.port")));
//        factory.setUsePool(true);
//        factory.setPoolConfig(jedisPoolConfig);
//        factory.afterPropertiesSet();
//        return factory;
//    }
//
//    @Bean
//    public RedisTemplate<String,String> redisTemplate(JedisConnectionFactory jedisConnectionFactory){
//        RedisTemplate redisTemplate = new RedisTemplate();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory);
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
//        return redisTemplate;
//    }

//    @Override
//    public void setEnvironment(Environment environment) {
//        this.environment = environment;
//    }
}
