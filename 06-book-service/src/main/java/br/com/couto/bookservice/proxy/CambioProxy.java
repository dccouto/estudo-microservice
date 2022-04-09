package br.com.couto.bookservice.proxy;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.couto.bookservice.response.Cambio;
//O feign junto com o eureka fazem o Load Balance da aplicação
@FeignClient(name = "cambio-service")
public interface CambioProxy {
	
	
	@GetMapping("/cambio-service/{value}/{from}/{to}")
	public ResponseEntity<Cambio> getCambio(@PathVariable("value") BigDecimal value,
			@PathVariable("from") String from,
			@PathVariable("to") String to
			);

}
