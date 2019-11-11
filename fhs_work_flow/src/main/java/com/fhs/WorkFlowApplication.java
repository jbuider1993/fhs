package com.fhs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;


/**
 * 工作流服务
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableFeignClients(basePackages = {"com.fhs"})
@MapperScan(basePackages = {"com.fhs.*.dao", "com.fhs.*.*.dao"})
@PropertySource("classpath:config.properties")
@EnableEurekaClient
@EnableDiscoveryClient
public class WorkFlowApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkFlowApplication.class, args);
    }
}
