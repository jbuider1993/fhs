package com.fhs.config;

import com.fhs.system.trans.FlowMessageListener;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

/**
 * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
 * @author  yutao
 * @version  [版本号, 2019/11/13 10:10:45]
 * @versio  1.0
 * Copyright (c) 2019 All Rights Reserved.
 * */
@Configuration
@EnableCaching
public class RedisConfig2 {

    @Bean
    MessageListenerAdapter listenerAdapter(FlowMessageListener receiver){
        MessageListenerAdapter result = new MessageListenerAdapter(receiver,"flowMsg");
        JdkSerializationRedisSerializer valSerializer =new JdkSerializationRedisSerializer();
        result.setSerializer(valSerializer);
        return result;
    }

}