package com.ebay.flight_booking.service;

import com.ebay.flight_booking.entity.Booking;
import com.ebay.flight_booking.entity.Flight;
import com.ebay.flight_booking.exception.FlightNotFoundException;
import com.ebay.flight_booking.exception.NoSeatsAvailableException;
import com.ebay.flight_booking.repository.BookingRepository;
import com.ebay.flight_booking.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceImplTest {

    private FlightRepository flightRepository;
    private BookingRepository bookingRepository;
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        flightRepository = mock(FlightRepository.class);
        bookingRepository = mock(BookingRepository.class);
        bookingService = new BookingServiceImpl(flightRepository, bookingRepository);
    }

    @Test
    void bookTicket_success() {
        Flight flight = new Flight("FL123", 1);
        when(flightRepository.findByFlightNumber("FL123")).thenReturn(Optional.of(flight));

        Booking booking = bookingService.bookTicket("FL123", "John Doe");

        assertNotNull(booking.getBookingId());
        assertEquals("FL123", booking.getFlightNumber());
        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    void bookTicket_flightNotFound() {
        when(flightRepository.findByFlightNumber("FL999")).thenReturn(Optional.empty());

        assertThrows(FlightNotFoundException.class, () ->
                bookingService.bookTicket("FL999", "John Doe"));
    }

    @Test
    void bookTicket_noSeatsAvailable() {
        Flight flight = new Flight("FL123", 1);
        flight.reserveSeat(); // seat already taken
        when(flightRepository.findByFlightNumber("FL123")).thenReturn(Optional.of(flight));

        assertThrows(NoSeatsAvailableException.class, () ->
                bookingService.bookTicket("FL123", "John Doe"));
    }
}