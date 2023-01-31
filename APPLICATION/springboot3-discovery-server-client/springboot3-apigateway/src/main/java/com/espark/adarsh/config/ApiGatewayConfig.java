package com.espark.adarsh.config;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//UNCOMMENT TO USE PROGRAMMATIC CONFIGURATION
//@Configuration
public class ApiGatewayConfig {

   // @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("service-one",r -> r.path("/api/wish/**")
                        .uri("lb://SPRING-CLOUD-CLIENT-SERVICE"))
                .route("service-two",r -> r.path("/api/wish/**")
                        .uri("lb://SPRING-CLOUD-CLIENT-SERVICE"))
                .build();
    }
}
