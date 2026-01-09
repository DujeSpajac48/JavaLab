package com.duje.javalab.bookapi.member;

import com.duje.javalab.bookapi.member.dto.MemberCreateRequest;
import com.duje.javalab.bookapi.member.dto.MemberResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponse create(@Valid @RequestBody MemberCreateRequest req) {
        return service.create(req);
    }
}
