package br.com.couto.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.couto.models.Cambio;
import br.com.couto.repository.CambioRepository;

@Service
public class CambioService {
	
	@Autowired
	private CambioRepository cambioRepository;

	public Cambio getCambio(String from, String to, BigDecimal value) {
		Cambio cambio = cambioRepository.findByFromAndTo(from, to);
		BigDecimal convertedValue = cambio.getConversionFactor().multiply(value).setScale(2, RoundingMode.CEILING);
		cambio.setConvertedValue(convertedValue);
		return cambio;
	}

}
