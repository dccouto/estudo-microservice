package br.com.couto.bookservice.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.couto.bookservice.models.Book;
import br.com.couto.bookservice.proxy.CambioProxy;
import br.com.couto.bookservice.response.Cambio;
import br.com.couto.bookservice.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("book-service")
public class BookController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CambioProxy cambioProxy;

	@Operation(summary = "Busca um livro específico pelo ID")
	@GetMapping("/{id}/{currency}")
	public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
		String port = environment.getProperty("local.server.port");
		
		
		Book book = bookService.findBook(id, currency);
		

		
		//feign
		Cambio cambio = cambioProxy.getCambio(book.getPrice(), "USD", currency).getBody();
		
		book.setPrice(cambio.getConvertedValue());
		book.setEnvironment("Book-service port: " + port + " | Cambio-service port:" + cambio.getEnvironment());
		book.setCurrency(currency);
		
		return book;
	}
	
	/*
	 * @GetMapping("/{id}/{currency}") public Book findBook(@PathVariable("id") Long
	 * id, @PathVariable("currency") String currency) {
	 * 
	 * Book book = bookService.findBook(id, currency);
	 * 
	 * HashMap<String, String> params = new HashMap<>(); params.put("price",
	 * book.getPrice().toString()); params.put("from", "USD"); params.put("to",
	 * currency);
	 * 
	 * 
	 * Cambio cambio = new RestTemplate()
	 * .getForEntity("http://localhost:8001/cambio-service/{price}/{from}/{to}",
	 * Cambio.class, params ) .getBody();
	 * 
	 * book.setPrice(cambio.getConvertedValue()); book.setCurrency(currency);
	 * 
	 * return book; }
	 */
	
}
