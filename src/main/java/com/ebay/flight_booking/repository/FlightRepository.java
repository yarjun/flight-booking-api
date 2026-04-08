package com.ebay.flight_booking.repository;

import com.ebay.flight_booking.entity.Flight;

import java.util.Optional;

/**
 * Repository interface for managing Flight data.
 */
public interface FlightRepository {

    /**
     * Saves a flight.
     *
     * @param flight flight to save
     */
    void save(Flight flight);

    /**
     * Finds a flight by flight number.
     *
     * @param flightNumber unique flight identifier
     * @return optional flight
     */
    Optional<Flight> findByFlightNumber(String flightNumber);
}