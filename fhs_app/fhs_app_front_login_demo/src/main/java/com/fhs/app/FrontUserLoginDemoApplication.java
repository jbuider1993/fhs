package com.fhs.app;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 微信的appid和secret可以在这里取
 * https://mp.weixin.qq.com/debug/cgi-bin/sandboxinfo?action=showinfo&t=sandbox/index
 */
@SpringBootApplication(scanBasePackages = {"com.fhs","com.alicp.jetcache"})
@MapperScan(basePackages = {"com.fhs.*.mapper", "com.fhs.*.*.mapper"})
@EnableConfigurationProperties
@ServletComponentScan(basePackages = "com.fhs")
@EnableRedisHttpSession
@EnableMethodCache(basePackages = "com.fhs")
@EnableFeignClients(basePackages = {"com.fhs"})
public class FrontUserLoginDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run( FrontUserLoginDemoApplication.class, args);
    }
}
