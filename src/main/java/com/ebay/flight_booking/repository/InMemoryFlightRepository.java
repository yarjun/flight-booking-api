package com.ebay.flight_booking.repository;

import com.ebay.flight_booking.entity.Flight;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory implementation of FlightRepository.
 */
@Repository
public class InMemoryFlightRepository implements FlightRepository {

    private final ConcurrentHashMap<String, Flight> flightStore = new ConcurrentHashMap<>();

    @Override
    public void save(Flight flight) {
        flightStore.put(flight.getFlightNumber(), flight);
    }

    @Override
    public Optional<Flight> findByFlightNumber(String flightNumber) {
        return Optional.ofNullable(flightStore.get(flightNumber));
    }
}