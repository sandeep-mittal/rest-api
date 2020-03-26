package com.excercise.rest.model;

public class Book {
    private int bookId;
    String bookName;
    String author;
    String type;

    public Book(){

    }
    public Book(int bookId, String bookName, String author, String type) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.type = type;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
