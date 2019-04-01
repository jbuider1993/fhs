package com.fhs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication()
@MapperScan(basePackages = {"com.fhs.*.dao", "com.fhs.*.*.dao"})
@EnableConfigurationProperties
@PropertySource("classpath:config.properties")
@EnableCircuitBreaker
@EnableRedisHttpSession
@EnableFeignClients(basePackages = {"com"})
@EnableEurekaClient
@EnableDiscoveryClient
@PropertySource("classpath:config.properties")
public class FhsFrameworkBaseBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run( FhsFrameworkBaseBusinessApplication.class, args);
    }
}
