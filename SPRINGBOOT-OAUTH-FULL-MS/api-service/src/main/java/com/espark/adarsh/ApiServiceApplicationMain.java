package com.espark.adarsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableEurekaClient
@EnableResourceServer
@EnableCircuitBreaker
@SpringBootApplication
public class ApiServiceApplicationMain {

	public static void main(String[] args) {
		SpringApplication.run(ApiServiceApplicationMain.class, args);
	}

}
