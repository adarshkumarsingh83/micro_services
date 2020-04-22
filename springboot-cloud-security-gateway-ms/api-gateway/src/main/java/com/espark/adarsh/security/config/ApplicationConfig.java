package com.espark.adarsh.security.config;

import com.espark.adarsh.security.bean.JwtConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }
}
