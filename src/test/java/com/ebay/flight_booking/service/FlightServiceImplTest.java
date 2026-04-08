package com.ebay.flight_booking.service;

import com.ebay.flight_booking.entity.Flight;
import com.ebay.flight_booking.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightServiceImplTest {

    private FlightRepository flightRepository;
    private FlightService flightService;

    @BeforeEach
    void setUp() {
        flightRepository = mock(FlightRepository.class);
        flightService = new FlightServiceImpl(flightRepository);
    }

    @Test
    void createFlight_success() {
        Flight flight = flightService.createFlight("FL123", 5);

        assertEquals("FL123", flight.getFlightNumber());
        assertEquals(5, flight.getTotalSeats());
        assertEquals(5, flight.getAvailableSeats());

        verify(flightRepository, times(1)).save(flight);
    }
}