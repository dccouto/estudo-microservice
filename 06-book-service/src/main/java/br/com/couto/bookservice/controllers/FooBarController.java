package br.com.couto.bookservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;

@Tag(name = "Foo bar") // http://localhost:8100/v3/api-docs
@Log4j2
@RestController
@RequestMapping("book-service")
public class FooBarController {
	
	@Operation(summary = "Foo bar")
	@GetMapping("/foo-bar")
	//@Retry(name ="foo-bar", fallbackMethod = "metodoDeFalha") //irá tentar por 5 vezes e depois irá chamar um método fallback em caso de falha
	//@CircuitBreaker(name ="default", fallbackMethod = "metodoDeFalha") //irá tentar por 5 vezes e depois irá chamar um método fallback em caso de falha
	//@RateLimiter(name = "default") //permite configurar a quantidade de chamadas permitidas em um determinado momento.
	@Bulkhead(name = "default")
	public String fooBar() {
		log.info("Requisição do foo-bar foi recebida.");
		//ResponseEntity<String> response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
		//return response.getBody();
		return "foo-barrrrr!!!!";
	}
	
	public String metodoDeFalha(Exception ex) {
		log.info("Requisição do fallbackMethod foo-bar foi recebida.");
		return "Requisição do fallbackMethod foo-bar foi recebida.";
	}

}
