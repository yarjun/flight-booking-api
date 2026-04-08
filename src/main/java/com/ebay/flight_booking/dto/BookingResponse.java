package com.ebay.flight_booking.dto;

/**
 * Response DTO for booking confirmation.
 */
public class BookingResponse {

    private final String bookingId;
    private final String message;

    public BookingResponse(String bookingId, String message) {
        this.bookingId = bookingId;
        this.message = message;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getMessage() {
        return message;
    }
}
