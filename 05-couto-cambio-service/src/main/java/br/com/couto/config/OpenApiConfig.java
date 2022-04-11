package br.com.couto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition(info = 
	@Info(title = "Cambio Service API",
			version = "v1",
			description = "Documentação para o cambio service API"
	)
)
/**
 * Esse é o endereço padrão para acessar o json gerado pelo open-api 
 * http://localhost:{PORT}/v3/api-docs
 * Para o swagger é o endereço
 * http://localhost:{PORT}/swagger-ui.html
 * */

public class OpenApiConfig {
	
	@Bean
	@Lazy(false)//para forçar que carregue assim que o api gateway estiver inicializando
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components())
				.info(new io.swagger.v3.oas.models.info.Info()
						.title("Cambio Service API")
						.version("v1")
						.license(new License()
								.name("Apache 2.0")
								.url("http://github.com/dccouto")
						)
				);
	}

}
