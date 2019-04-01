package com.fhs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@MapperScan(basePackages = {"com.fhs.*.dao", "com.fhs.*.*.dao"})
@EnableConfigurationProperties
@EnableRedisHttpSession
@EnableFeignClients(basePackages = {"com.fhs"})
@ComponentScan({"com.fhs"})
public class FhsMiniAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run( FhsMiniAdminApplication.class, args);
    }
}
