package com.example.BikeRentalSystem.RestEndpoints;

public class BookingsEndpoints extends ConstantRestEndpoints{
    public static final String GET_ALL=API+"bookings";
    public static final String POST=API+"bookings";
    public static final String GET_BY_EMAIL=API+"booking/{email}";
    public static final String GET_BY_ID=API+"bookings/{id}";
    public static final String DELETE_BY_ID=API+"bookings/{id}";
}
