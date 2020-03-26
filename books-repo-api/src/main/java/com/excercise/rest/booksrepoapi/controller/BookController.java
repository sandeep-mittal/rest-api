package com.excercise.rest.booksrepoapi.controller;


import com.excercise.rest.booksrepoapi.dto.Book;
import com.excercise.rest.booksrepoapi.exception.BookRepoApiException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/book-repo")
public interface BookController {


    @GetMapping (path="/getBooks", produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<List<Book>> getAllBooks() throws BookRepoApiException;


    @PostMapping(path="/addBook", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    String addBook(@RequestBody(required = false) Book b)  throws BookRepoApiException;


    @GetMapping(value = "/removeBook", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> removeBook(@RequestParam(required = false) Long bookId)  throws BookRepoApiException;


    @GetMapping(value = "/getBook/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Book> getBook(@PathVariable Long bookId)  throws BookRepoApiException;


}
