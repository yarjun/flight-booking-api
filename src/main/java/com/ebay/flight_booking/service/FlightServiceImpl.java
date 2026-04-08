package com.ebay.flight_booking.service;

import com.ebay.flight_booking.entity.Flight;
import com.ebay.flight_booking.repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Implementation of FlightService.
 */
@Service
public class FlightServiceImpl implements FlightService {

    private static final Logger log = LoggerFactory.getLogger(FlightServiceImpl.class);

    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Flight createFlight(String flightNumber, int totalSeats) {
        log.info("Creating flight with flightNumber={}, totalSeats={}", flightNumber, totalSeats);

        Flight flight = new Flight(flightNumber, totalSeats);
        flightRepository.save(flight);

        log.info("Flight created successfully for flightNumber={}", flightNumber);
        return flight;
    }
}