package com.ebay.flight_booking.dto;

/**
 * Request DTO for creating a flight.
 */
public class CreateFlightRequest {

    private String flightNumber;
    private int totalSeats;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }
}