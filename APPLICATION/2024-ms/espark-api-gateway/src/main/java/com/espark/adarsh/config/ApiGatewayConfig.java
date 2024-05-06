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
                .route(r -> r.path("/wish")
                        //Pre and Post Filters provided by Spring Cloud Gateway
                        .filters(f -> f.addRequestHeader("wish-request", "wish-request-header")
                                .addResponseHeader("wish-response", "wish-response-header"))
                        .uri("lb://espark-api-aggregator"))

                .route(r -> r.path("/greet")
                        //Pre and Post Filters provided by Spring Cloud Gateway
                        .filters(f -> f.addRequestHeader("greet-request", "greet-request-header")
                                .addResponseHeader("greet-response", "greet-response-header"))
                        .uri("lb://espark-api-aggregator"))

                .build();
    }

}
