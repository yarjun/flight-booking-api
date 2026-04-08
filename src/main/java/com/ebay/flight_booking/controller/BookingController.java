package com.ebay.flight_booking.controller;

import com.ebay.flight_booking.dto.BookingRequest;
import com.ebay.flight_booking.dto.BookingResponse;
import com.ebay.flight_booking.entity.Booking;
import com.ebay.flight_booking.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for booking operations.
 */
@RestController
@RequestMapping("/bookings")
public class BookingController {

    private static final Logger log = LoggerFactory.getLogger(BookingController.class);

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Books a ticket for a given flight.
     *
     * @param request booking request
     * @return booking confirmation
     */
    @PostMapping
    public ResponseEntity<BookingResponse> bookTicket(@RequestBody BookingRequest request) {

        log.info("Received booking request for flightNumber={}, passengerName={}",
                request.getFlightNumber(), request.getPassengerName());

        Booking booking = bookingService.bookTicket(
                request.getFlightNumber(),
                request.getPassengerName()
        );

        BookingResponse response = new BookingResponse(
                booking.getBookingId(),
                "Booking successful"
        );

        return ResponseEntity.ok(response);
    }
}