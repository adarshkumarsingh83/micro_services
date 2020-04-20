package com.espark.adarsh.security.filter;

import com.espark.adarsh.security.bean.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenAuthenticationSecurityFilter implements GlobalFilter, Ordered {
    
    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {
        log.info("label=JwtTokenAuthenticationSecurityFilter.filter() executed ");


        // 1. get the authentication header. Tokens are supposed to be passed in the authentication header
        List<String> header = exchange.getRequest().getHeaders().get(jwtConfig.getHeader());
        

        String token = this.getHeader(header);
        if (token == null) {
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        log.info("Last Post Global Filter");
                    }));
        }

        try {    // exceptions might be thrown in creating the claims if for example the token is expired

            // 4. Validate the token
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSecret().getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            if (username != null) {
                @SuppressWarnings("unchecked")
                List<String> authorities = (List<String>) claims.get("authorities");

                // 5. Create auth object
                // UsernamePasswordAuthenticationToken: A built-in object, used by spring to
                // represent the current authenticated / being authenticated user.
                // It needs a list of authorities, which has type of GrantedAuthority interface,
                // where SimpleGrantedAuthority is an implementation of that interface
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, null, authorities.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()));

                // 6. Authenticate the user
                // Now, user is authenticated
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (Exception e) {
            // In case of failure. Make sure it's clear;
            // so guarantee user won't be authenticated
            SecurityContextHolder.clearContext();
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

    private String getHeader(List<String> headers) {
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

}
