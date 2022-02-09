package com.example.BikeRentalSystem.service;
import com.example.BikeRentalSystem.entities.Bookings;
import com.example.BikeRentalSystem.repository.BookingsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;




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

    public Bookings getBookingById(ObjectId id){
        return this.bookingsRepository.findBy_id(id);
    }
    public List<Bookings> getBookingsByEmail(String email){
        return this.bookingsRepository.findByEmail(email);
    }

    public Bookings addBooking(Bookings booking){
        return this.bookingsRepository.save(booking);
    }

    public String deleteBooking(ObjectId id){
        try{
            Bookings booking=this.bookingsRepository.findBy_id(id);
            this.bookingsRepository.deleteById(String.valueOf(id));
            return "Booking deleted successfully";
        }
        catch (Exception e){
            return "No such bookings";
        }
    }




}
