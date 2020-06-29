package com.espark.adarsh.config;

import com.espark.adarsh.handler.JsonExceptionHandler;
import com.espark.adarsh.security.filter.JwtTokenAuthenticationSecurityFilter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public JwtTokenAuthenticationSecurityFilter jwtTokenAuthenticationSecurityFilter() {
        return new JwtTokenAuthenticationSecurityFilter();
    }

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/auth/", "/auth/**")
                        .uri("lb://auth-service")
                        .id("auth-service"))
                .route(r -> r.path("/api/**")
                        //Pre and Post Filters provided by Spring Cloud Gateway
                        .filters(f -> f.filters(jwtTokenAuthenticationSecurityFilter())
                                .addRequestHeader("user-request", "user-request-header")
                                .addResponseHeader("user-response", "user-response-header"))
                        .uri("lb://user-service")
                        .id("user-service"))
                .build();
    }


    @Primary
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                                             ServerCodecConfigurer serverCodecConfigurer) {
        JsonExceptionHandler jsonExceptionHandler = new JsonExceptionHandler();
        jsonExceptionHandler.setViewResolvers(viewResolversProvider.getIfAvailable(Collections::emptyList));
        jsonExceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
        jsonExceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());
        return jsonExceptionHandler;
    }


}
