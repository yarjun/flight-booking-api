package com.ebay.flight_booking.service;

import com.ebay.flight_booking.entity.Flight;

/**
 * Service interface for flight operations.
 */
public interface FlightService {

    /**
     * Creates a new flight.
     *
     * @param flightNumber unique flight identifier
     * @param totalSeats   total number of seats
     * @return created flight
     */
    Flight createFlight(String flightNumber, int totalSeats);
}