package com.example.BikeRentalSystem.controller;

import com.example.BikeRentalSystem.entities.Bookings;
import com.example.BikeRentalSystem.service.BookingsService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookingRestControllerTest {

    @InjectMocks
    private BookingRestController bookingRestController;

    @Mock
    private BookingsService bookingsService;


    @Test
    void addBooking() {
        MockHttpServletRequest request=new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Bookings bookings=new Bookings();
        bookings.set_id(ObjectId.get());
        bookings.setStatus(false);
        bookings.setEmail("krshivamranjan@gmail.com");
        bookings.setStationId(2);
        bookings.setVehicleId(String.valueOf(ObjectId.get()));

        when(bookingsService.addBooking(any(Bookings.class))).thenReturn(bookings);
        ResponseEntity<Bookings> response= bookingRestController.addBooking(bookings);
        assertEquals(response.getBody(),bookings);
    }

    @Test
    void getBookingById(){
        Bookings bookings=new Bookings();
        bookings.set_id(ObjectId.get());
        bookings.setStatus(false);
        bookings.setEmail("krshivamranjan@gmail.com");
        bookings.setStationId(2);
        bookings.setVehicleId(String.valueOf(ObjectId.get()));

        when(bookingsService.getBookingById(new ObjectId(bookings.get_id()))).thenReturn(bookings);

        ResponseEntity<Bookings> responseEntity = bookingRestController.getBookingById(new ObjectId(bookings.get_id()));
        assertEquals(responseEntity.getBody(),bookings);
        assertEquals(responseEntity.getStatusCodeValue(),200);

    }


}