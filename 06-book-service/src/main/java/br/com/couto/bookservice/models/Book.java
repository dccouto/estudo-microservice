package br.com.couto.bookservice.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name="book")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="author", nullable = false, length = 180)
	private String author;
	@Column(name="launch_date", nullable = false)
	private LocalDate launchDate;
	@Column(nullable = false)
	private BigDecimal price;
	@Column(nullable = false, length = 250)
	private String title;
	@Transient
	private String currency;
	@Transient
	private String environment;
	
}
