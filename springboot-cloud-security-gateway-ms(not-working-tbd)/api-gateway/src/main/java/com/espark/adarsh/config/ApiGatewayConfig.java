package com.espark.adarsh.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/auth/","/auth/**")
                        //.uri("lb://AUTH-SERVICE")
                        .uri("lb://auth-service")
                        .id("auth-service"))
                .route(r -> r.path("/api/**")
                        //Pre and Post Filters provided by Spring Cloud Gateway
                        .filters(f -> f.addRequestHeader("user-request", "user-request-header")
                                .addResponseHeader("user-response", "user-response-header"))
                        .uri("lb://user-service")
                        .id("user-service"))
                .build();
    }


}
