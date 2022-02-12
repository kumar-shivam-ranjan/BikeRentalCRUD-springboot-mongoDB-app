package com.example.BikeRentalSystem.repository;

import com.example.BikeRentalSystem.entities.Bookings;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingsRepository extends MongoRepository<Bookings,String> {

    public List<Bookings> findByEmail(String email);
    public List<Bookings> findByStatus(boolean status);

}
