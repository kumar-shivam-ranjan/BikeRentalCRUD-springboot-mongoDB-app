package com.example.BikeRentalSystem.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Station")
public class Station {
	
	@Transient
	public static final String SEQUENCE_NAME="user_sequence";
	
	@Id
	private int stationId;
	private String head;
	private String contact;
	private String street;
	private String city;
	private String state;
	private int pincode;

	public Station(int stationId, String head, String contact, String street, String city, String state, int pincode) {
		this.stationId = stationId;
		this.head = head;
		this.contact = contact;
		this.street = street;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "Station{" +
				"stationId=" + stationId +
				", head='" + head + '\'' +
				", contact='" + contact + '\'' +
				", street='" + street + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				", pincode=" + pincode +
				'}';
	}

	public Station() {
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
}
