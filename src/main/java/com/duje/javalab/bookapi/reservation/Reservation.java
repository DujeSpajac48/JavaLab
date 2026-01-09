package com.duje.javalab.bookapi.reservation;

import com.duje.javalab.bookapi.book.Book;
import com.duje.javalab.bookapi.member.Member;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Jedan član može imati više rezervacija
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // Jedna knjiga može biti rezervirana od strane više članova
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ReservationStatus status = ReservationStatus.PENDING;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    private Instant fulfilledAt;

    public Reservation() {}

    public Reservation(Member member, Book book) {
        this.member = member;
        this.book = book;
        this.status = ReservationStatus.PENDING;
        this.createdAt = Instant.now();
    }

    public Long getId() { return id; }
    public Member getMember() { return member; }
    public Book getBook() { return book; }
    public ReservationStatus getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getFulfilledAt() { return fulfilledAt; }

    public void setStatus(ReservationStatus status) { this.status = status; }
    public void setFulfilledAt(Instant fulfilledAt) { this.fulfilledAt = fulfilledAt; }
}
