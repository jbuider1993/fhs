package com.fhs.config;

import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.ConverterUtils;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.redis.jedis.JedisLockProvider;
import net.javacrumbs.shedlock.spring.ScheduledLockConfiguration;
import net.javacrumbs.shedlock.spring.ScheduledLockConfigurationBuilder;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author qiuhang
 * @Description:spring boot的Schedule config配置，加入线程池操作
 * @date 2018/9/27 14:53
 * @versio 1.0
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 **/
@Configuration
@EnableScheduling
public class TaskSchedulerConfig implements EnvironmentAware {

    @Value("${fhs.task.pool-size}")
    private int  poolSize;

    @Value("${fhs.task.default-lock-Minutes}")
    private int  defaultLockMinutes;

    private RelaxedPropertyResolver propertyResolver;

    @Bean
    public ScheduledLockConfiguration taskScheduler(LockProvider lockProvider) {
        return ScheduledLockConfigurationBuilder
                .withLockProvider(lockProvider)
                .withPoolSize(poolSize)
                .withDefaultLockAtMostFor(Duration.ofMinutes(defaultLockMinutes))
                .build();
    }
    @Bean
    public LockProvider lockProvider() {
        JedisPoolConfig config = new JedisPoolConfig();
        // 因为这里不需要太多连接，故写死
        config.setMaxTotal(5);
        config.setMaxIdle(5);
        config.setMinIdle(1);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);

        String host = propertyResolver.getProperty("spring.redis.host");
        //如果配置了单个redis，优先用单个redis
        if(CheckUtils.isNotEmpty(host))
        {
            Integer port = Integer.valueOf(propertyResolver.getProperty("spring.redis.port"));
            JedisPool jedisPool = new JedisPool(new GenericObjectPoolConfig(),host, port,20000,propertyResolver.getProperty("spring.redis.password"));
            return new JedisLockProvider(jedisPool);
        }
        // 如果没有配置单个redis 就用哨兵
        Set<String> sentinelsSet = new HashSet<>();
        String[] sentinels = propertyResolver.getProperty("spring.redis.sentinel.nodes").split(",");
        sentinelsSet.addAll(Arrays.asList(sentinels));
        JedisSentinelPool jedisPool = new
                JedisSentinelPool( propertyResolver.getProperty("spring.redis.sentinel.master"), sentinelsSet, config, ConverterUtils.toInt(
                propertyResolver.getProperty("spring.redis.timeout")), propertyResolver.getProperty("spring.redis.password"),0);
        return new JedisLockProvider(jedisPool);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, (String)null);
    }
}
