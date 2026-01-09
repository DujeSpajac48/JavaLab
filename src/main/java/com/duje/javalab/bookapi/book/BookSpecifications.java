package com.duje.javalab.bookapi.book;

import org.springframework.data.jpa.domain.Specification;

public final class BookSpecifications {
    private BookSpecifications() {}

    public static Specification<Book> titleContains(String title) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Book> authorContains(String author) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("author")), "%" + author.toLowerCase() + "%");
    }

    public static Specification<Book> genreEquals(String genre) {
        return (root, query, cb) ->
                cb.equal(cb.lower(root.get("genre")), genre.toLowerCase());
    }

    public static Specification<Book> publishedYearEquals(Integer year) {
        return (root, query, cb) ->
                cb.equal(root.get("publishedYear"), year);
    }
}
