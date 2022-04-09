package br.com.couto.bookservice.config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition(info = 
	@Info(title = "Book Service API",
			version = "v1",
			description = "Documentação para o book service API"
	)
)
/**
 * Esse é o endereço padrão para acessar o json gerado pelo open-api 
 * http://localhost:8100/v3/api-docs
 * */

public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components())
				.info(new io.swagger.v3.oas.models.info.Info()
						.title("Book Service API")
						.version("v1")
						.license(new License()
								.name("Apache 2.0")
								.url("http://github.com/dccouto")
						)
				);
	}

}
