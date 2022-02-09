package com.example.BikeRentalSystem.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class Users {
	
	@Transient
	public static final String SEQUENCE_NAME="user_sequence";
	
	
	@Id
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String drivingLicense;
	private String contact;
	private String role;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDrivingLicense() {
		return drivingLicense;
	}
	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", drivingLicense=" + drivingLicense + ", contact=" + contact + ", role="
				+ role + "]";
	}
	public Users(int id, String firstName, String lastName, String email, String password, String drivingLicense,
			String contact, String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.drivingLicense = drivingLicense;
		this.contact = contact;
		this.role = role;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
