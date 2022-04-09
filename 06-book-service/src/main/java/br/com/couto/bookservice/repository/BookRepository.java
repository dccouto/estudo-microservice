package br.com.couto.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.couto.bookservice.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
