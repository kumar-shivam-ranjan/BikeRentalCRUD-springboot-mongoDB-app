package com.example.BikeRentalSystem.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.BikeRentalSystem.entities.Station;

@Repository
public interface StationRepository extends MongoRepository<Station, Integer> {

	public List<Station> findByCity(String city);
}
