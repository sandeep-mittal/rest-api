package com.excercise.rest.dto;

import com.excercise.rest.model.Book;

import java.util.List;

public class Reply {


    private ReplyStatus status;
    private String message;


    public Reply(){

    }

    public Reply(ReplyStatus status, String message){
        this.status=status;
        this.message=message;
    }

    public ReplyStatus getStatus() {
        return status;
    }

    public void setStatus(ReplyStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
