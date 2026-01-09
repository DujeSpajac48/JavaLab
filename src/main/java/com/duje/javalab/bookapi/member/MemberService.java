package com.duje.javalab.bookapi.member;

import com.duje.javalab.bookapi.common.InvalidRequestException;
import com.duje.javalab.bookapi.member.dto.MemberCreateRequest;
import com.duje.javalab.bookapi.member.dto.MemberResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository repo;

    public MemberService(MemberRepository repo) {
        this.repo = repo;
    }

    public MemberResponse create(MemberCreateRequest req) {
        if (repo.existsByEmail(req.getEmail())) {
            throw new InvalidRequestException("Email already exists: " + req.getEmail());
        }
        Member saved = repo.save(new Member(req.getFullName(), req.getEmail()));
        return new MemberResponse(saved.getId(), saved.getFullName(), saved.getEmail());
    }
}
