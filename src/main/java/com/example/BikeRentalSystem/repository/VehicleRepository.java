package com.example.BikeRentalSystem.repository;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.BikeRentalSystem.entities.Vehicle;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String>{

	public Vehicle findBy_id(ObjectId _id);
	
	@Query("{_id: { $in: ?0 }}")
	public List<Vehicle> findAllVehiclesWithGivenIds(List<String> ids);
}
