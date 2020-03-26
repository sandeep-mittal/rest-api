package com.excercise.rest.controller;

import com.excercise.rest.dto.Reply;
import com.excercise.rest.exception.LibraryApiException;
import com.excercise.rest.model.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api( tags = {"library-api"})
@RequestMapping("/library-api")
public interface LibraryController {


    @ApiOperation(value="Add new book",response = Book.class)
    @ApiResponses(value={
            @ApiResponse(code = 201,message = "added {bookName} successfully.", response = Reply.class),
            @ApiResponse(code = 500,message = "bookName: null found, string expected"),
            @ApiResponse(code = 500,message = "author: null found, string expected"),
            @ApiResponse(code = 500,message = "type: null found, string expected", response = Reply.class),

    })
    @PostMapping(path="/addBook", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Reply> addBook(@RequestBody(required = false) Book b) throws LibraryApiException;

    @ApiOperation(value="Remove book using Id",response = Reply.class)
    @ApiResponses(value={
            @ApiResponse(code = 201,message = "book {bookName} is removed successfully.", response = Reply.class),
            @ApiResponse(code = 500,message = "book {ID} is missing.", response = Reply.class),
            @ApiResponse(code = 500,message = "book {ID} not found.", response = Reply.class),

    })
    @GetMapping(value = "/removeBook/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Reply> removeBook(@PathVariable(required = false) int bookId) throws LibraryApiException;




}
