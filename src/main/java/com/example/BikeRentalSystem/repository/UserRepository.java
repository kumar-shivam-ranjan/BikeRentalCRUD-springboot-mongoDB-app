package com.example.BikeRentalSystem.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.BikeRentalSystem.entities.Users;

@Repository
public interface UserRepository extends MongoRepository<Users, Integer> {


	@Query("{'email' : ?0}")
	public Users getUserByEmail(String email);
}
