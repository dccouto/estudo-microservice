package br.com.couto.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
@RefreshScope //para atualizar dinamicamente quando o profile mudou
@ConfigurationProperties("greeting-service")
public class GreetingConfiguration {

	private String greeting; 
	private String defaultValue; 
}
