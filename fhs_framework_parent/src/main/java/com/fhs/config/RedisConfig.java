package com.fhs.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
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


}