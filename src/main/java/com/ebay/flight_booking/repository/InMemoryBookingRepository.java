package com.ebay.flight_booking.repository;

import com.ebay.flight_booking.entity.Booking;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory implementation of BookingRepository.
 */
@Repository
public class InMemoryBookingRepository implements BookingRepository {

    private final ConcurrentHashMap<String, Booking> bookingStore = new ConcurrentHashMap<>();

    @Override
    public void save(Booking booking) {
        bookingStore.put(booking.getBookingId(), booking);
    }
}