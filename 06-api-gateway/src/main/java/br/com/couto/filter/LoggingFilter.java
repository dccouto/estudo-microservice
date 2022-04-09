package br.com.couto.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

/**
 * Classe só pra demonstrar uma utilização do filtro
 * 
 * */
@Component
@Log4j2
public class LoggingFilter implements GlobalFilter {

	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		log.info("Original request path -> {}", exchange.getRequest().getPath());
		return chain.filter(exchange);
	}

}
