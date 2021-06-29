package com.espark.adarsh.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PreFilter extends AbstractGatewayFilterFactory<PreFilter.Config> {

	public PreFilter() {
		super(Config.class);
	}
	
	@Override
	public GatewayFilter apply(Config config) {
		log.info("label= PreFilter  apply()");
		
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest().mutate().header("PRE-FILTER-HEADER", "PRE-FILTER-HEADER").build();
			return chain.filter(exchange.mutate().request(request).build());
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
