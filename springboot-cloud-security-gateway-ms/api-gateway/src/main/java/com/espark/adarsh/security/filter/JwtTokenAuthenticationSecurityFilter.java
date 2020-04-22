package com.espark.adarsh.security.filter;

import com.espark.adarsh.security.bean.JwtConfig;
import com.sun.net.httpserver.Filter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

@Slf4j
public class JwtTokenAuthenticationSecurityFilter implements GatewayFilter, Ordered {

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {
        log.info("label=JwtTokenAuthenticationSecurityFilter.filter() executed ");

        log.info("REQUEST URL :===> " + exchange.getRequest().getPath().value());

        String token = this.getHeader(exchange);
        if (token == null) {
            ServerHttpResponse serverHttpResponse = exchange.getResponse();
            serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        } else {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSecret().getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            if (username == null || (Long) claims.get("lastData") < System.currentTimeMillis()) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
               // return this.getUnAuthorizedResponse(exchange,chain);
            }
        }
        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    log.info("Last Post Global Filter");
                }));
    }

    @Override
    public int getOrder() {
        return -1;
    }

    private String getHeader(ServerWebExchange exchange) {

        List<String> headers = exchange.getRequest()
                .getHeaders()
                .get(jwtConfig.getHeader());

        if (headers != null && !headers.isEmpty()) {
            Optional<String> token = headers.stream()
                    .filter(header -> header.startsWith(jwtConfig.getPrefix()))
                    .map(header -> header.replace(jwtConfig.getPrefix(), ""))
                    .findFirst();

            return token.isPresent() ? token.get() : null;
        } else {
            return null;
        }
    }


    private Mono<Void> getUnAuthorizedResponse(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        ServerHttpResponse originalResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(fluxBody.map(dataBuffer -> {
                        // probably should reuse buffers
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        byte[] uppedContent = new String(content, Charset.forName("UTF-8")).toUpperCase().getBytes();
                        return bufferFactory.wrap(uppedContent);
                    }));
                }
                return super.writeWith(body);
            }
        };
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }
}
