package com.duje.javalab.bookapi.notification;

import com.duje.javalab.bookapi.member.MemberRepository;
import com.duje.javalab.bookapi.common.MemberNotFoundException;
import com.duje.javalab.bookapi.notification.dto.NotificationResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class NotificationService {

    private final NotificationRepository repo;
    private final MemberRepository memberRepo;

    public NotificationService(NotificationRepository repo, MemberRepository memberRepo) {
        this.repo = repo;
        this.memberRepo = memberRepo;
    }

    public List<NotificationResponse> getByMember(Long memberId) {
        if (!memberRepo.existsById(memberId)) {
            throw new MemberNotFoundException("Member not found: id=" + memberId);
        }

        return repo.findByMemberIdOrderByCreatedAtDesc(memberId).stream()
                .map(n -> new NotificationResponse(n.getId(), memberId, n.getMessage(), n.getCreatedAt(), n.isRead()))
                .toList();
    }
}
