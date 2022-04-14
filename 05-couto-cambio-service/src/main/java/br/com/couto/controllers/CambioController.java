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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Tag(name="Cambio service")
@RestController
@RequestMapping("cambio-service")
public class CambioController {
	
	//Com ele é possível pear várias informações da aplicação
	@Autowired
	private Environment environment;

	@Autowired
	private CambioService cambioService;
	

	@Operation(summary = "Converter cambio por moeda")
	@GetMapping("{value}/{from}/{to}")
	public ResponseEntity<Cambio> getCambio(
			@PathVariable("value") BigDecimal value,
			@PathVariable("from") String from,
			@PathVariable("to") String to
			) {
		log.info("Cambio foi chamado com os parâmetros -> {}, {} and {} ", value, from, to);
		var port = environment.getProperty("local.server.port");
		Cambio cambio = cambioService.getCambio(from, to, value);
		cambio.setEnvironment(port);
		return ResponseEntity.ok(cambio);
	}

}
