package com.duje.javalab.bookapi.notification;

import com.duje.javalab.bookapi.member.Member;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false, length = 500)
    private String message;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    @Column(nullable = false)
    private boolean read = false;

    public Notification() {}

    public Notification(Member member, String message) {
        this.member = member;
        this.message = message;
        this.createdAt = Instant.now();
        this.read = false;
    }

    public Long getId() { return id; }
    public Member getMember() { return member; }
    public String getMessage() { return message; }
    public Instant getCreatedAt() { return createdAt; }
    public boolean isRead() { return read; }

    public void setRead(boolean read) { this.read = read; }
}
