package com.fhs.config;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.redis.spring.RedisLockProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Duration;
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

    private Environment environment;



    @Bean
    public LockProvider lockProvider(RedisConnectionFactory connectionFactory) {
        return new RedisLockProvider(connectionFactory);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
