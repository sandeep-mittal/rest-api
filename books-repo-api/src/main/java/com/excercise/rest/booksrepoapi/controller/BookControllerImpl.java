package com.excercise.rest.booksrepoapi.controller;

import com.excercise.rest.booksrepoapi.dto.Book;
import com.excercise.rest.booksrepoapi.exception.BookRepoApiException;
import com.excercise.rest.booksrepoapi.service.BookRepoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class BookControllerImpl implements BookController {

    Logger log = LoggerFactory.getLogger(BookControllerImpl.class);

    @Autowired
    BookRepoService service;


    @Override
    public ResponseEntity<List<Book>> getAllBooks() throws BookRepoApiException {

        List<Book> bookList = service.getAllBooks();

        return ResponseEntity.created(null).body(bookList);

    }

    @Override
    public String addBook(Book b) throws BookRepoApiException {

       Book newBook = service.addBook(b);
        return "Added " + newBook.getName() + " successfully.";
    }

    @Override
    public ResponseEntity<String> removeBook( Long bookId) throws BookRepoApiException {
        log.info("ID is "+ bookId);
        String response=service.removeBook(bookId);
        return ResponseEntity.created(null).body(response);
    }

    @Override
    public ResponseEntity<Book> getBook( Long bookId) throws BookRepoApiException {
        Book book = service.getbook(bookId);

        return ResponseEntity.created(null).contentType(MediaType.APPLICATION_JSON).body((Book)book);
    }
}
