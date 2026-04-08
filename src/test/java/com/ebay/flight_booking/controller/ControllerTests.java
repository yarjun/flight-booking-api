package com.ebay.flight_booking.controller;

import com.ebay.flight_booking.dto.BookingRequest;
import com.ebay.flight_booking.dto.CreateFlightRequest;
import com.ebay.flight_booking.entity.Flight;
import com.ebay.flight_booking.service.BookingService;
import com.ebay.flight_booking.service.FlightService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {FlightController.class, BookingController.class})
class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    @MockBean
    private BookingService bookingService;

    @Test
    void createFlightController() throws Exception {
        CreateFlightRequest request = new CreateFlightRequest();
        request.setFlightNumber("FL123");
        request.setTotalSeats(10);

        when(flightService.createFlight("FL123", 10)).thenReturn(new Flight("FL123", 10));

        mockMvc.perform(post("/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"flightNumber\":\"FL123\",\"totalSeats\":10}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.flightNumber").value("FL123"));
    }

    @Test
    void bookTicketController() throws Exception {
        // Could mock bookingService and assert JSON response
    }
}