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
                .route(r -> r.path("/api/message/**")
                        //Pre and Post Filters provided by Spring Cloud Gateway
                        .filters(f -> f.addRequestHeader("message-request", "message-request-header")
                                .addResponseHeader("message-response", "message-response-header"))
                        .uri("lb://espark-api-aggregator"))
                .route(r -> r.path("/api/details/**")
                        //Pre and Post Filters provided by Spring Cloud Gateway
                        .filters(f -> f.addRequestHeader("details-request", "details-request-header")
                                .addResponseHeader("details-response", "details-response-header"))
                        .uri("lb://espark-api-aggregator"))
                .route(r -> r.path("/api/wish/**")
                        //Pre and Post Filters provided by Spring Cloud Gateway
                        .filters(f -> f.addRequestHeader("wish-request", "wish-request-header")
                                .addResponseHeader("wish-response", "wish-response-header"))
                        .uri("lb://espark-api-aggregator"))

                .route(r -> r.path("/api/greet/**")
                        //Pre and Post Filters provided by Spring Cloud Gateway
                        .filters(f -> f.addRequestHeader("greet-request", "greet-request-header")
                                .addResponseHeader("greet-response", "greet-response-header"))
                        .uri("lb://espark-api-aggregator"))
                .route(r -> r.path("/api/employee/**")
                        //Pre and Post Filters provided by Spring Cloud Gateway
                        .filters(f -> f.addRequestHeader("employee-request", "employee-request-header")
                                .addResponseHeader("employee-response", "employee-response-header"))
                        .uri("lb://espark-api-aggregator"))
                .route(r -> r.path("/api/address/**")
                        //Pre and Post Filters provided by Spring Cloud Gateway
                        .filters(f -> f.addRequestHeader("address-request", "address-request-header")
                                .addResponseHeader("address-response", "address-response-header"))
                        .uri("lb://espark-api-aggregator"))
                .build();
    }

}
