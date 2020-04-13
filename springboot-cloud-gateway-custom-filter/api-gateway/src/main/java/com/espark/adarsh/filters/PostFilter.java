package com.espark.adarsh.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Slf4j
@Component
public class PostFilter extends AbstractGatewayFilterFactory<PostFilter.Config> {

    public PostFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
		log.info("label= PostFilter  apply()");

        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                ServerHttpResponse response = exchange.getResponse();
                exchange.getResponse().getHeaders().add("POST-FILTER-HEADER","POST-FILTER-HEADER");
                HttpHeaders headers = response.getHeaders();
                headers.forEach((k, v) -> {
					log.info("label=PostFilter key={}:value={}", k, v);
                });
            }));
        };
    }

    public static class Config {

        private String name;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}