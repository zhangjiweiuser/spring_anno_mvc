package com.zhang.jiwei.jedis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @author jiwei.zhang
 * @DATE 2018/1/3 0003
 */
public class RedisMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {
        String channel = new String(message.getChannel());
        String body = new String(message.getBody());
//        String bytes2 = new String(bytes);
        System.out.println(String.format("channel:%s,body:%s", channel, body));
    }
}
