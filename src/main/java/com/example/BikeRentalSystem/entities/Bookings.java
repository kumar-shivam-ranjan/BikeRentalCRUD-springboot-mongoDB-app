package com.example.BikeRentalSystem.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Document(collection = "bookings")
public class Bookings {

    @Id
    private ObjectId _id;
    private String email;
    private String vehicleId;
    private int stationId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkInDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkOutDate;
    private boolean status;
    private long cost;
    public Bookings() {
    }

    @Override
    public String toString() {
        return "Bookings{" +
                "_id=" + _id +
                ", email='" + email + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", stationId=" + stationId +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", status=" + status +
                ", cost=" + cost +
                '}';
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public Bookings(ObjectId _id, String email, String vehicleId, int stationId, Date checkInDate, Date checkOutDate, boolean status, long cost) {
        this._id = _id;
        this.email = email;
        this.vehicleId = vehicleId;
        this.stationId = stationId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;
        this.cost = cost;
    }

}
