package com.fhs.app;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication(scanBasePackages = {"com.fhs","com.alicp.jetcache"})
@MapperScan(basePackages = {"com.fhs.*.mapper", "com.fhs.*.*.mapper"})
@EnableConfigurationProperties
@EnableRedisHttpSession
@EnableMethodCache(basePackages = "com.fhs")
@EnableFeignClients(basePackages = {"com.fhs"})
@ServletComponentScan(basePackages = {"com.fhs","org.apache.shiro"})
public class SingleApplication {

    public static void main(String[] args) {
        SpringApplication.run( SingleApplication.class, args);
    }
}
