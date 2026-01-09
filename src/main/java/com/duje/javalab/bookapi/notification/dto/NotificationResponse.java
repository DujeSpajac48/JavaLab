package com.duje.javalab.bookapi.notification.dto;

import java.time.Instant;

public class NotificationResponse {
    private Long id;
    private Long memberId;
    private String message;
    private Instant createdAt;
    private boolean read;

    public NotificationResponse(Long id, Long memberId, String message, Instant createdAt, boolean read) {
        this.id = id;
        this.memberId = memberId;
        this.message = message;
        this.createdAt = createdAt;
        this.read = read;
    }

    public Long getId() { return id; }
    public Long getMemberId() { return memberId; }
    public String getMessage() { return message; }
    public Instant getCreatedAt() { return createdAt; }
    public boolean isRead() { return read; }
}
