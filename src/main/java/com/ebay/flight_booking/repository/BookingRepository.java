package com.ebay.flight_booking.repository;

import com.ebay.flight_booking.entity.Booking;

/**
 * Repository interface for managing Booking data.
 */
public interface BookingRepository {

    /**
     * Saves a booking.
     *
     * @param booking booking to save
     */
    void save(Booking booking);
}
