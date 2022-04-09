package br.com.couto.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.couto.models.Cambio;
import br.com.couto.services.CambioService;

@RestController
@RequestMapping("cambio-service")
public class CambioController {
	
	//Com ele é possível pear várias informações da aplicação
	@Autowired
	private Environment environment;

	@Autowired
	private CambioService cambioService;
	

	@GetMapping("{value}/{from}/{to}")
	public ResponseEntity<Cambio> getCambio(
			@PathVariable("value") BigDecimal value,
			@PathVariable("from") String from,
			@PathVariable("to") String to
			) {
		
		var port = environment.getProperty("local.server.port");
		Cambio cambio = cambioService.getCambio(from, to, value);
		cambio.setEnvironment(port);
		return ResponseEntity.ok(cambio);
	}

}
