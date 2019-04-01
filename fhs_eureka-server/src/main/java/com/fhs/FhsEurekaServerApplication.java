package com.fhs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class FhsEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FhsEurekaServerApplication.class, args);
	}
}
