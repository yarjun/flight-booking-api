package com.ebay.flight_booking.dto;

import java.time.LocalDateTime;

/**
 * Standard error response structure.
 */
public class ErrorResponse {

    private final String message;
    private final String errorCode;
    private final LocalDateTime timestamp;

    public ErrorResponse(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}