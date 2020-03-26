package com.excercise.rest.controller;

import com.excercise.rest.dto.Reply;
import com.excercise.rest.dto.ReplyStatus;
import com.excercise.rest.exception.LibraryApiException;
import com.excercise.rest.model.Book;
import com.excercise.rest.service.LibraryApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryControllerImpl implements LibraryController {

    Logger log = LoggerFactory.getLogger(LibraryControllerImpl.class);
    @Autowired
    LibraryApiService service;


    @Override
    public ResponseEntity<Reply> addBook(Book b) throws LibraryApiException {
        log.info("Calling Rest API Service to add book... ");
        String responseMessage = service.processAddBook(b);

        return ResponseEntity.created(null).body(new Reply(ReplyStatus.SUCCESS,responseMessage));
    }

    @Override
    public ResponseEntity<Reply> removeBook(int bookId) throws LibraryApiException {
        log.info("Calling Rest API Service to remove the book... ");
        String responseMessage = service.processRemoveBook(bookId);

        return ResponseEntity.created(null).body(new Reply(ReplyStatus.SUCCESS,responseMessage));
    }


}
