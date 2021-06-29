package com.espark.adarsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplicationMain {
    public static void main(String args[]) {
        SpringApplication.run(ConfigServerApplicationMain.class, args);
    }
}
