package br.com.couto.bookservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.couto.bookservice.models.Book;
import br.com.couto.bookservice.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public Book findBook(Long id, String currency) {
		
		Optional<Book> optionalBook = bookRepository.findById(id);
		Book book = optionalBook.orElseThrow();
		
		return book;
		
	}
}
