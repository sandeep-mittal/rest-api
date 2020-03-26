package com.excercise.rest.exception;

public class LibraryApiException extends  Exception{
    public LibraryApiException(){

    }

    public LibraryApiException(String exceptionMessage){
        super(exceptionMessage);
    }
}
