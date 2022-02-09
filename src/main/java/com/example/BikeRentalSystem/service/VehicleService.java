package com.example.BikeRentalSystem.service;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.BikeRentalSystem.entities.Vehicle;
import com.example.BikeRentalSystem.repository.VehicleRepository;

@Service
public class VehicleService {

	

	@Autowired
	private VehicleRepository vehicleRepository;

	public VehicleService(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}

	public List<Vehicle> getAllVehicles()
	{
		return vehicleRepository.findAll();
	}
	
	
	public Vehicle addVehicle(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}


	public String deleteVehicle(ObjectId id) {

		try {
			Vehicle vehicle=vehicleRepository.findBy_id(id);
			vehicleRepository.delete(vehicle);
			return "Vehicle Deleted Successfully";
		} catch (Exception e) {			
			return "No such Vehicle exists";
		}
		
	}


	public Vehicle updateVehicle(Vehicle vehicle, ObjectId id) {
		try {
			Vehicle vehicleOld=vehicleRepository.findBy_id(id);
			vehicleOld.setDescription(vehicle.getDescription());
			vehicleOld.setMileage(vehicle.getMileage());
			vehicleOld.setModel(vehicle.getModel());
			vehicleOld.setVehicleType(vehicle.getVehicleType());
			vehicleOld.setEngine(vehicle.getEngine());
			vehicleOld.setFuelType(vehicle.getFuelType());
			vehicleOld.setImageUrl(vehicle.getImageUrl());
			Vehicle newUpdatedVehicle=vehicleRepository.save(vehicleOld);
			return newUpdatedVehicle;
		} catch (Exception e) {
			return null;
		}
	}


	public Vehicle getVehicleById(ObjectId id) {
		try {
			Vehicle vehicle=vehicleRepository.findBy_id(id);
			return vehicle;
		} catch (Exception e) {
			return null;
		}
	}
	
}
