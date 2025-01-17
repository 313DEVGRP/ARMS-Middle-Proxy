package com.arms.config.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;


public class LoginErrorFilter implements WebFilter {

    @Value("${spring.security.auth.success.redirect-url}")
    private String redirectUrl;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        //브라우저에서 history(-1) 시에 login?error 로 가버리는 현상이 있어서 -> index.html 로 강제로 리턴 해주기 위한 내용
        String requestPath = exchange.getRequest().getURI().getPath();
        if ("/login".equals(requestPath)&&exchange.getRequest().getQueryParams().containsKey("error")) {
            exchange.getResponse().setStatusCode(HttpStatus.FOUND);
            exchange.getResponse().getHeaders().setLocation(URI.create(redirectUrl));

            return Mono.empty();
        }

        return chain.filter(exchange);
    }
}
