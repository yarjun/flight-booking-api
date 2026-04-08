package com.ebay.flight_booking.service;

import com.ebay.flight_booking.entity.Booking;

/**
 * Service interface for booking operations.
 */
public interface BookingService {

    /**
     * Books a seat on a flight.
     *
     * @param flightNumber  flight identifier
     * @param passengerName passenger name
     * @return booking confirmation
     */
    Booking bookTicket(String flightNumber, String passengerName);
}