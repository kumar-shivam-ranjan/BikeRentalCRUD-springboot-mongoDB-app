package com.example.BikeRentalSystem.service;
import com.example.BikeRentalSystem.entities.Bookings;
import com.example.BikeRentalSystem.repository.BookingsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class BookingsService {

    @Autowired
    private BookingsRepository bookingsRepository;

    public BookingsService(BookingsRepository bookingsRepository) {

        this.bookingsRepository = bookingsRepository;
    }

    public List<Bookings> getAllBookings(){

        return this.bookingsRepository.findAll();
    }

    public List<Bookings> getBookingsByStatus(boolean status){

        return  this.bookingsRepository.findByStatus(status);
    }

    public Optional<Bookings> getBookingById(String id){
        return this.bookingsRepository.findById(id);
    }


    public List<Bookings> getBookingsByEmail(String email){

        return this.bookingsRepository.findByEmail(email);
    }

    public Bookings addBooking(Bookings booking){

        return this.bookingsRepository.save(booking);
    }

    public String deleteBooking(String id){
        try{
            this.bookingsRepository.findById(id).get();
            this.bookingsRepository.deleteById(id);
            return "Booking deleted successfully";
        }
        catch (Exception e){
            return "No such bookings";
        }
    }




}
