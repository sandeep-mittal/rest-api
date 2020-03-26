package com.excercise.rest.booksrepoapi.service;

import com.excercise.rest.booksrepoapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excercise.rest.booksrepoapi.dto.Book;
import com.excercise.rest.booksrepoapi.exception.BookRepoApiException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class BookRepoService  {

    @Autowired
    BookRepository repository;

    public List<Book>  getAllBooks(){

        return repository.findAll();
    }

    public Book  addBook(Book book){

        return (Book) repository.save(book);
    }

    public String removeBook(Long bookId){
        repository.deleteById(bookId);
        return "Book is removed successfully";
    }

    @GetMapping("/books/{id}")
   public Book getbook(@PathVariable Long id) throws BookRepoApiException{
        return repository.findById(id)
                .orElseThrow(() -> new BookRepoApiException("Book Id not found"));
    }
}
