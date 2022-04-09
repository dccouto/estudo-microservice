package br.com.couto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.couto.models.Cambio;

public interface CambioRepository extends JpaRepository<Cambio, Long>{

	Cambio findByFromAndTo(String from, String to);
}
