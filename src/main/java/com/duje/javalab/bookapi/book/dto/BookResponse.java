package com.duje.javalab.bookapi.book.dto;

public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private Integer publishedYear;
    private String genre;
    private String isbn;

    public BookResponse(Long id, String title, String author, Integer publishedYear, String genre, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.genre = genre;
        this.isbn = isbn;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public Integer getPublishedYear() { return publishedYear; }
    public String getGenre() { return genre; }
    public String getIsbn() { return isbn; }
}
