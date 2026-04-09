package com.ebay.flight_booking.controller;

import com.ebay.flight_booking.dto.BookingRequest;
import com.ebay.flight_booking.dto.CreateFlightRequest;
import com.ebay.flight_booking.dto.BookingResponse;
import com.ebay.flight_booking.entity.Booking;
import com.ebay.flight_booking.entity.Flight;
import com.ebay.flight_booking.service.BookingService;
import com.ebay.flight_booking.service.FlightService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ControllerTests {

    private MockMvc mockMvc;

    private FlightService flightService;
    private BookingService bookingService;

    private FlightController flightController;
    private BookingController bookingController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        // Mock services
        flightService = Mockito.mock(FlightService.class);
        bookingService = Mockito.mock(BookingService.class);

        // Initialize controllers
        flightController = new FlightController(flightService);
        bookingController = new BookingController(bookingService);

        // Build MockMvc with both controllers and exception handler
        mockMvc = MockMvcBuilders.standaloneSetup(flightController, bookingController)
                .setControllerAdvice(new com.ebay.flight_booking.exception.GlobalExceptionHandler())
                .build();
    }

    @Test
    void createFlightController() throws Exception {
        CreateFlightRequest request = new CreateFlightRequest();
        request.setFlightNumber("FL123");
        request.setTotalSeats(10);

        Flight flight = new Flight("FL123", 10);
        when(flightService.createFlight("FL123", 10)).thenReturn(flight);

        mockMvc.perform(post("/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.flightNumber").value("FL123"))
                .andExpect(jsonPath("$.totalSeats").value(10))
                .andExpect(jsonPath("$.bookedSeats").value(0));
    }

    @Test
    void bookTicketController() throws Exception {
        BookingRequest request = new BookingRequest();
        request.setFlightNumber("FL123");
        request.setPassengerName("John Doe");

        // Mock Booking returned by service
        Booking booking = new Booking("FL123", "John Doe");
        booking = Mockito.spy(booking); // spy to mock getBookingId
        Mockito.doReturn(1L).when(booking).getBookingId();
        when(bookingService.bookTicket(anyString(), anyString())).thenReturn(booking);

        mockMvc.perform(post("/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingId").value(1))
                .andExpect(jsonPath("$.message").value("Booking successful"));
    }
}