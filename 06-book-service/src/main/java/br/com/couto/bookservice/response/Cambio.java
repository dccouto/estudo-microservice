package br.com.couto.bookservice.response;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cambio {

	private Long id;
	private String from;
	private String to;
	private BigDecimal conversionFactor;
	private BigDecimal convertedValue;
	private String environment;
	
}
