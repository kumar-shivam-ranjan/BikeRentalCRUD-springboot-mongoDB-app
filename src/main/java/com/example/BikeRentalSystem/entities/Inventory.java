package com.example.BikeRentalSystem.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Inventory")


public class Inventory {
	
	
	@Transient
	public static final String SEQUENCE_NAME="user_sequence";
	
	@Id
	private int id;
	private String vehicleId;
	private int stationId;
	private int quantity;
	private boolean availabilityStatus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public int getStationId() {
		return stationId;
	}
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public boolean isAvailabilityStatus() {
		return availabilityStatus;
	}
	public void setAvailabilityStatus(boolean availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}
	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}
	@Override
	public String toString() {
		return "Inventory [id=" + id + ", vehicleId=" + vehicleId + ", stationId=" + stationId + ", quantity="
				+ quantity + ", availabilityStatus=" + availabilityStatus + "]";
	}
	public Inventory(int id, String vehicleId, int stationId, int quantity, boolean availabilityStatus) {
		super();
		this.id = id;
		this.vehicleId = vehicleId;
		this.stationId = stationId;
		this.quantity = quantity;
		this.availabilityStatus = availabilityStatus;
	}
	public Inventory() {
		super();
		
	}
	
	
}
