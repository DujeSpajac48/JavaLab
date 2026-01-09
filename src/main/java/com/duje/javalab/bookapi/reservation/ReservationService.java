package com.duje.javalab.bookapi.reservation;

import com.duje.javalab.bookapi.book.Book;
import com.duje.javalab.bookapi.book.BookRepository;
import com.duje.javalab.bookapi.common.BookNotFoundException;
import com.duje.javalab.bookapi.common.MemberNotFoundException;
import com.duje.javalab.bookapi.common.ReservationNotFoundException;
import com.duje.javalab.bookapi.member.Member;
import com.duje.javalab.bookapi.member.MemberRepository;
import com.duje.javalab.bookapi.notification.Notification;
import com.duje.javalab.bookapi.notification.NotificationRepository;
import com.duje.javalab.bookapi.reservation.dto.ReservationCreateRequest;
import com.duje.javalab.bookapi.reservation.dto.ReservationResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepo;
    private final MemberRepository memberRepo;
    private final BookRepository bookRepo;
    private final NotificationRepository notificationRepo;

    public ReservationService(ReservationRepository reservationRepo,
                              MemberRepository memberRepo,
                              BookRepository bookRepo,
                              NotificationRepository notificationRepo) {
        this.reservationRepo = reservationRepo;
        this.memberRepo = memberRepo;
        this.bookRepo = bookRepo;
        this.notificationRepo = notificationRepo;
    }

    public ReservationResponse create(ReservationCreateRequest req) {
        Member member = memberRepo.findById(req.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException("Member not found: id=" + req.getMemberId()));

        Book book = bookRepo.findById(req.getBookId())
                .orElseThrow(() -> new BookNotFoundException("Book not found: id=" + req.getBookId()));

        Reservation saved = reservationRepo.save(new Reservation(member, book));

        return toResponse(saved);
    }

    public ReservationResponse fulfill(Long reservationId) {
        Reservation r = reservationRepo.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found: id=" + reservationId));

        r.setStatus(ReservationStatus.FULFILLED);
        r.setFulfilledAt(Instant.now());

        Reservation saved = reservationRepo.save(r);

        String msg = "Knjiga '" + saved.getBook().getTitle() + "' je sada dostupna za posudbu.";
        notificationRepo.save(new Notification(saved.getMember(), msg));

        return toResponse(saved);
    }

    private ReservationResponse toResponse(Reservation r) {
        return new ReservationResponse(
                r.getId(),
                r.getMember().getId(),
                r.getBook().getId(),
                r.getStatus(),
                r.getCreatedAt(),
                r.getFulfilledAt()
        );
    }
}
