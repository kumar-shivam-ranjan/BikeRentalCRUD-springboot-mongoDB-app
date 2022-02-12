package com.example.BikeRentalSystem.service;

import java.util.List;
import java.util.Optional;

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


	public void deleteVehicle(String id) {
		this.vehicleRepository.deleteById(id);
	}


	public Vehicle updateVehicle(Vehicle vehicle, String id) {
			vehicle.setId(id);
			return this.vehicleRepository.save(vehicle);
	}


	public Optional<Vehicle> getVehicleById(String id) {
		return this.vehicleRepository.findById(id);
	}
}
