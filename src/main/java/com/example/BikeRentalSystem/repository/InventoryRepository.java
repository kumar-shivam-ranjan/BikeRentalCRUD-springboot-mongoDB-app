package com.example.BikeRentalSystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.example.BikeRentalSystem.entities.Inventory;

@Repository
public interface InventoryRepository extends MongoRepository<Inventory,Integer>{

	public List<Inventory> findByStationId(int sid);
}
