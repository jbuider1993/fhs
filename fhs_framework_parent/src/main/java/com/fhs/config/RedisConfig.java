package com.fhs.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fhs.system.trans.TransMessageListener;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Description:redis序列化配置
 * @author  zhangqiang
 * @version  [版本号, 2018/08/21 16:10:45]
 * @versio  1.0
 * Copyright (c) 2017 All Rights Reserved.
 * */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * @desc redis缓存，设置过期时间
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        //设置缓存过期时间
        rcm.setDefaultExpiration(60);//秒
        return rcm;
    }
    /**
     * RedisTemplate配置
     * @param factory RedisConnectionFactory对象
     * @return redisTemplate对象
     */
    @Bean("redisTemplate")
    @Primary
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(factory);
        //定义key的序列化方式
        JdkSerializationRedisSerializer valSerializer =new JdkSerializationRedisSerializer();
        template.setValueSerializer(valSerializer);
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        template.setKeySerializer(keySerializer);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * redis消息监听器容器
     * 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
     * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
     * @param connectionFactory redis连接池
     * @param listenerAdapter 监听适配器
     * @return
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,  MessageListenerAdapter listenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅多个频道
        container.addMessageListener(listenerAdapter,new PatternTopic("trans"));
        //序列化对象（特别注意：发布的时候需要设置序列化；订阅方也需要设置序列化）
        StringRedisSerializer seria = new StringRedisSerializer();
        container.setTopicSerializer(seria);
        return container;
    }

    //表示监听一个频道
    @Bean
    MessageListenerAdapter listenerAdapter(TransMessageListener receiver){
        return new MessageListenerAdapter(receiver,"handelMsg");
    }


}