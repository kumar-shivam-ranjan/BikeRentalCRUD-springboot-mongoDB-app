package com.example.BikeRentalSystem.controller;
import com.example.BikeRentalSystem.entities.Bookings;
import com.example.BikeRentalSystem.service.BookingsService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class BookingRestController {

    @Autowired
    private BookingsService bookingsService;

    @GetMapping("bookings")
    public ResponseEntity<List<Bookings>> getAllBookings(){
        List<Bookings> bookings=this.bookingsService.getAllBookings();
        if(bookings.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.of(Optional.of(bookings));
        }
    }
    @PostMapping("bookings")
    public ResponseEntity<Bookings> addBooking(@RequestBody Bookings booking){
        try{
                Bookings newBooking=this.bookingsService.addBooking(booking);
                return ResponseEntity.of(Optional.of(newBooking));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("bookings/{id}")
    public ResponseEntity<Bookings> getBookingById(@PathVariable("id") String id){
        try{
            Optional<Bookings> bookingById = this.bookingsService.getBookingById(id);
            if(bookingById.isPresent())
            return ResponseEntity.of(Optional.of(bookingById.get()));
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("booking/{email}")
    public ResponseEntity<List<Bookings>> getBookingByEmail(@PathVariable("email") String email){

        try {
            List<Bookings> bookings=this.bookingsService.getBookingsByEmail(email);
            if(bookings.size()<=0){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            else {
                return ResponseEntity.of(Optional.of(bookings));
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("bookings/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable("id") String id){
        try {
            this.bookingsService.getBookingById(id);
            this.bookingsService.deleteBooking(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }



}
