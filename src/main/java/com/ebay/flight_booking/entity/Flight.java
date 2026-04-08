package com.ebay.flight_booking.entity;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents a Flight in the system.
 * <p>
 * This class maintains seat availability and ensures thread-safe updates
 * using {@link AtomicInteger}.
 * </p>
 */
public class Flight {

    private final String flightNumber;
    private final int totalSeats;
    private final AtomicInteger availableSeats;

    /**
     * Constructs a Flight instance.
     *
     * @param flightNumber unique flight identifier
     * @param totalSeats   total number of seats in the flight
     */
    public Flight(String flightNumber, int totalSeats) {
        this.flightNumber = flightNumber;
        this.totalSeats = totalSeats;
        this.availableSeats = new AtomicInteger(totalSeats);
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats.get();
    }

    /**
     * Attempts to reserve a seat.
     *
     * @return true if seat successfully reserved, false if no seats available
     */
    public boolean reserveSeat() {
        while (true) {
            int current = availableSeats.get();
            if (current <= 0) {
                return false;
            }
            if (availableSeats.compareAndSet(current, current - 1)) {
                return true;
            }
        }
    }
}