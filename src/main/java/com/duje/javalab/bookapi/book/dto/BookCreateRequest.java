package com.duje.javalab.bookapi.book.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookCreateRequest {

    @NotBlank @Size(max = 255)
    private String title;

    @NotBlank @Size(max = 255)
    private String author;

    @Min(0)
    private Integer publishedYear;

    @Size(max = 100)
    private String genre;

    @Size(max = 50)
    private String isbn;

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public Integer getPublishedYear() { return publishedYear; }
    public String getGenre() { return genre; }
    public String getIsbn() { return isbn; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPublishedYear(Integer publishedYear) { this.publishedYear = publishedYear; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
}
