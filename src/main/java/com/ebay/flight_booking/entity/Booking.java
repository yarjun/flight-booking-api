package com.ebay.flight_booking.entity;

import java.util.UUID;

/**
 * Represents a booking made by a passenger.
 */
public class Booking {

    private final String bookingId;
    private final String flightNumber;
    private final String passengerName;

    /**
     * Constructs a Booking instance.
     *
     * @param flightNumber  flight identifier
     * @param passengerName name of the passenger
     */
    public Booking(String flightNumber, String passengerName) {
        this.bookingId = UUID.randomUUID().toString();
        this.flightNumber = flightNumber;
        this.passengerName = passengerName;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }
}