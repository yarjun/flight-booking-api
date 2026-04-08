package com.ebay.flight_booking.exception;

/**
 * Exception thrown when no seats are available for booking.
 */
public class NoSeatsAvailableException extends RuntimeException {

    public NoSeatsAvailableException(String message) {
        super(message);
    }
}