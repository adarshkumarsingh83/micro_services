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

                .route(r -> r.path("/employee/**")
                        //Pre and Post Filters provided by Spring Cloud Gateway
                        .filters(f -> f.addRequestHeader("employee-request", "employee-request-header")
                                .addResponseHeader("employee-response", "employee-response-header"))
                        .uri("http://localhost:8082/")
                        .id("employee-service"))

                .route(r -> r.path("/address/**")
                        //Pre and Post Filters provided by Spring Cloud Gateway
                        .filters(f -> f.addRequestHeader("address-request", "address-request-header")
                                .addResponseHeader("address-response", "address-response-header"))
                        .uri("http://localhost:8081/")
                        .id("address-service"))

                .build();
    }


}
