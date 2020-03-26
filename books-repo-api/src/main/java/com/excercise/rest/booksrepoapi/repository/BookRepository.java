package com.excercise.rest.booksrepoapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.excercise.rest.booksrepoapi.dto.Book;


public interface BookRepository extends JpaRepository<Book, Long> {
}
