package com.duje.javalab.bookapi.member.dto;

public class MemberResponse {
    private Long id;
    private String fullName;
    private String email;

    public MemberResponse(Long id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
}
