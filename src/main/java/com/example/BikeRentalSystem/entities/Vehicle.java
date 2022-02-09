package com.example.BikeRentalSystem.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Vehicles")
public class Vehicle {
	
    @Id
	private ObjectId _id;
	private String vehicleType;
	private String fuelType;
	private String engine;
	private int mileage;
	private String description;
	private String model;
	private String imageUrl;
	private int dailyPrice;
	private int weeklyPrice;

	public Vehicle(ObjectId _id, String vehicleType, String fuelType, String engine, int mileage, String description, String model, String imageUrl, int dailyPrice, int weeklyPrice) {
		this._id = _id;
		this.vehicleType = vehicleType;
		this.fuelType = fuelType;
		this.engine = engine;
		this.mileage = mileage;
		this.description = description;
		this.model = model;
		this.imageUrl = imageUrl;
		this.dailyPrice = dailyPrice;
		this.weeklyPrice = weeklyPrice;
	}

	public Vehicle() {
	}

	public String get_id() {
		return _id.toHexString();
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(int dailyPrice) {
		this.dailyPrice = dailyPrice;
	}

	public int getWeeklyPrice() {
		return weeklyPrice;
	}

	public void setWeeklyPrice(int weeklyPrice) {
		this.weeklyPrice = weeklyPrice;
	}

	@Override
	public String toString() {
		return "Vehicle{" +
				"_id=" + _id +
				", vehicleType='" + vehicleType + '\'' +
				", fuelType='" + fuelType + '\'' +
				", engine='" + engine + '\'' +
				", mileage=" + mileage +
				", description='" + description + '\'' +
				", model='" + model + '\'' +
				", imageUrl='" + imageUrl + '\'' +
				", dailyPrice=" + dailyPrice +
				", weeklyPrice=" + weeklyPrice +
				'}';
	}
}
