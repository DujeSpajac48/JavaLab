package com.duje.javalab.bookapi.reservation;

import com.duje.javalab.bookapi.reservation.dto.ReservationCreateRequest;
import com.duje.javalab.bookapi.reservation.dto.ReservationResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    // POST /api/reservations - Dodavanje nove rezervacije za člana
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponse create(@Valid @RequestBody ReservationCreateRequest req) {
        return service.create(req);
    }

    // PUT /api/reservations/{id}/fulfill - Označavanje rezervacije kao izvršene i slanje obavijesti
    @PutMapping("/{id}/fulfill")
    public ReservationResponse fulfill(@PathVariable Long id) {
        return service.fulfill(id);
    }
}
