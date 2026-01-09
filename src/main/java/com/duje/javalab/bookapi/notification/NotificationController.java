package com.duje.javalab.bookapi.notification;

import com.duje.javalab.bookapi.notification.dto.NotificationResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    // GET /notifications/member/{memberId} - Dohvat svih obavijesti za člana
    @GetMapping("/member/{memberId}")
    public List<NotificationResponse> getByMember(@PathVariable Long memberId) {
        return service.getByMember(memberId);
    }
}
