package com.ebay.flight_booking.controller;

import com.ebay.flight_booking.dto.CreateFlightRequest;
import com.ebay.flight_booking.entity.Flight;
import com.ebay.flight_booking.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for flight operations.
 */
@RestController
@RequestMapping("/flights")
public class FlightController {

    private static final Logger log = LoggerFactory.getLogger(FlightController.class);

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    /**
     * Creates a new flight.
     *
     * @param request flight creation request
     * @return created flight
     */
    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody CreateFlightRequest request) {

        log.info("Received request to create flight: {}", request.getFlightNumber());

        Flight flight = flightService.createFlight(
                request.getFlightNumber(),
                request.getTotalSeats()
        );

        return new ResponseEntity<>(flight, HttpStatus.CREATED);
    }
}