package com.duje.javalab.bookapi.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MemberCreateRequest {

    @NotBlank @Size(max = 120)
    private String fullName;

    @NotBlank @Email @Size(max = 180)
    private String email;

    public String getFullName() { return fullName; }
    public String getEmail() { return email; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
}
