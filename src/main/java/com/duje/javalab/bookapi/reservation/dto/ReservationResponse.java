package com.duje.javalab.bookapi.reservation.dto;

import com.duje.javalab.bookapi.reservation.ReservationStatus;

import java.time.Instant;

public class ReservationResponse {
    private Long id;
    private Long memberId;
    private Long bookId;
    private ReservationStatus status;
    private Instant createdAt;
    private Instant fulfilledAt;

    public ReservationResponse(Long id, Long memberId, Long bookId, ReservationStatus status, Instant createdAt, Instant fulfilledAt) {
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.status = status;
        this.createdAt = createdAt;
        this.fulfilledAt = fulfilledAt;
    }

    public Long getId() { return id; }
    public Long getMemberId() { return memberId; }
    public Long getBookId() { return bookId; }
    public ReservationStatus getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getFulfilledAt() { return fulfilledAt; }
}
