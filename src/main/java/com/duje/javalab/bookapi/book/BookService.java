package com.duje.javalab.bookapi.book;

import com.duje.javalab.bookapi.book.dto.BookCreateRequest;
import com.duje.javalab.bookapi.book.dto.BookResponse;
import com.duje.javalab.bookapi.book.dto.BookUpdateRequest;
import com.duje.javalab.bookapi.common.BookNotFoundException;
import com.duje.javalab.bookapi.common.InvalidRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public Page<BookResponse> getAll(String title, String author, String genre, Integer publishedYear, Pageable pageable) {
        if (pageable.getPageSize() > 100) {
            throw new InvalidRequestException("size must be <= 100");
        }

        Specification<Book> spec = (root, query, cb) -> cb.conjunction();

        if (title != null && !title.isBlank()) {
            spec = spec.and(BookSpecifications.titleContains(title));
        }
        if (author != null && !author.isBlank()) {
            spec = spec.and(BookSpecifications.authorContains(author));
        }
        if (genre != null && !genre.isBlank()) {
            spec = spec.and(BookSpecifications.genreEquals(genre));
        }
        if (publishedYear != null) {
            spec = spec.and(BookSpecifications.publishedYearEquals(publishedYear));
        }

        return repo.findAll(spec, pageable)
                .map(b -> new BookResponse(
                        b.getId(),
                        b.getTitle(),
                        b.getAuthor(),
                        b.getPublishedYear(),
                        b.getGenre(),
                        b.getIsbn()
                ));
    }

    public BookResponse getById(Long id) {
        Book b = repo.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found: id=" + id));

        return new BookResponse(b.getId(), b.getTitle(), b.getAuthor(), b.getPublishedYear(), b.getGenre(), b.getIsbn());
    }

    public BookResponse create(BookCreateRequest req) {
        if (req.getIsbn() != null && !req.getIsbn().isBlank() && repo.existsByIsbn(req.getIsbn())) {
            throw new InvalidRequestException("ISBN already exists: " + req.getIsbn());
        }

        Book b = new Book(req.getTitle(), req.getAuthor(), req.getPublishedYear(), req.getGenre(), req.getIsbn());
        Book saved = repo.save(b);

        return new BookResponse(saved.getId(), saved.getTitle(), saved.getAuthor(),
                saved.getPublishedYear(), saved.getGenre(), saved.getIsbn());
    }

    public BookResponse update(Long id, BookUpdateRequest req) {
        Book b = repo.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found: id=" + id));

        if (req.getIsbn() != null && !req.getIsbn().isBlank()) {
            repo.findByIsbn(req.getIsbn()).ifPresent(existing -> {
                if (!existing.getId().equals(id)) {
                    throw new InvalidRequestException("ISBN already exists: " + req.getIsbn());
                }
            });
        }

        b.setTitle(req.getTitle());
        b.setAuthor(req.getAuthor());
        b.setPublishedYear(req.getPublishedYear());
        b.setGenre(req.getGenre());
        b.setIsbn(req.getIsbn());

        Book saved = repo.save(b);

        return new BookResponse(saved.getId(), saved.getTitle(), saved.getAuthor(),
                saved.getPublishedYear(), saved.getGenre(), saved.getIsbn());
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new BookNotFoundException("Book not found: id=" + id);
        }
        repo.deleteById(id);
    }
}
