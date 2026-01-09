package com.duje.javalab.bookapi.book;

import com.duje.javalab.bookapi.book.dto.BookCreateRequest;
import com.duje.javalab.bookapi.book.dto.BookResponse;
import com.duje.javalab.bookapi.book.dto.BookUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    // /api/books?title=1984&genre=Drama&publishedYear=1949&page=0&size=10&sort=title,asc
    @GetMapping
    public Page<BookResponse> getAll(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer publishedYear,
            Pageable pageable
    ) {
        return service.getAll(title, author, genre, publishedYear, pageable);
    }

    @GetMapping("/{id}")
    public BookResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse create(@Valid @RequestBody BookCreateRequest req) {
        return service.create(req);
    }

    @PutMapping("/{id}")
    public BookResponse update(@PathVariable Long id, @Valid @RequestBody BookUpdateRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
