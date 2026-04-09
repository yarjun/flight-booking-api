package com.ebay.flight_booking.service;

import com.ebay.flight_booking.entity.Booking;
import com.ebay.flight_booking.entity.Flight;
import com.ebay.flight_booking.repository.BookingRepository;
import com.ebay.flight_booking.repository.FlightRepository;
import com.ebay.flight_booking.exception.FlightNotFoundException;
import com.ebay.flight_booking.exception.NoSeatsAvailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Implementation of BookingService.
 */

@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);

    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;

    public BookingServiceImpl(FlightRepository flightRepository,
                              BookingRepository bookingRepository) {
        this.flightRepository = flightRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking bookTicket(String flightNumber, String passengerName) {

        log.info("Booking request received for flightNumber={}, passengerName={}",
                flightNumber, passengerName);

        Flight flight = flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(() -> {
                    log.error("Flight not found for flightNumber={}", flightNumber);
                    return new FlightNotFoundException("Flight not found: " + flightNumber);
                });

        boolean seatReserved = flight.reserveSeat();

        if (!seatReserved) {
            log.error("No seats available for flightNumber={}", flightNumber);
            throw new NoSeatsAvailableException("No seats available for flight: " + flightNumber);
        }

        Booking booking = new Booking(flightNumber, passengerName);
        bookingRepository.save(booking);

        log.info("Booking successful. bookingId={}, flightNumber={}",
                booking.getBookingId(), flightNumber);

        return booking;
    }
}