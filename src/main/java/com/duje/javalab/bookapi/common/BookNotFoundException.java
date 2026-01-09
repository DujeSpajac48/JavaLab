package com.duje.javalab.bookapi.common;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) { super(message); }
}
